package com.chl.bookmanager.controller;

import com.chl.bookmanager.common.Const;
import com.chl.bookmanager.domain.*;
import com.chl.bookmanager.exception.*;
import com.chl.bookmanager.mapper.*;
import com.chl.bookmanager.service.BookBorrowService;
import com.chl.bookmanager.service.BookPredeterService;
import com.chl.bookmanager.service.FavouriteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author:Administrator
 * @date:2018/12/25/025
 */
@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Autowired
    private BookBorrowMapper bookBorrowMapper;
    @Autowired
    private BookBorrowService bookBorrowService;
    @Autowired
    private FavouriteService favouriteService;
    @Autowired
    private BookPredeterService bookPredeterService;
    @Autowired
    private BookTypeMapper bookTypeMapper;
    @Autowired
    private BookBackUpMapper bookBackUpMapper;
    @Autowired
    private BookPredeterMapper bookPredeterMapper;

    @GetMapping("{search}")
    public String getBooks(@PathVariable String search, Model model,//@PathVariable相当于从http请求里获取这个属性并传入这个参数并作为路径的一部分
                           @RequestParam(defaultValue = "1") Integer pageNo,             //页码
                           @RequestParam(defaultValue = "2") Integer pageSize,          //一页显示数量
                           @RequestParam(defaultValue = "book_id ASC") String orderBy,//排序方式
                           @RequestParam(defaultValue = "1") Integer select,          //排序编号
                           @RequestParam(required = false) String selectYear,   //选择的年份
                           @RequestParam(required = false) String selectAuthor,  //选择的作者
                           @RequestParam(required = false) String selectCountry,  //选择的语种
                           @RequestParam(defaultValue = "1") Integer returnPage
    ) {
        Example e = new Example(BookInfo.class);
        Example.Criteria c = e.createCriteria();
        if (!StringUtils.isEmpty(search)) {
            c.orLike("bookName", "%" + search + "%").orLike("author", "%" + search + "%");
        }
        List<String> year = null;
        if (selectYear != null && !selectYear.equals("")) {//如果限制年份，则添加限制年份
            Example.Criteria c2 = e.createCriteria();
            year = Arrays.asList(selectYear.split("="));
            year.forEach(item -> c2.orBetween("publishDate", new Date(Integer.parseInt(item.replace(",", "")) - 1900, 0, -1), new Date(Integer.parseInt(item.replace(",", "")) - 1900, 11, 31))
            );
            e.and(c2);
        }
        List<String> author = null;
        if (selectAuthor != null && !selectAuthor.equals("")) {//如果限制作者，则添加限制作者
            Example.Criteria c3 = e.createCriteria();
            author = Arrays.asList(selectAuthor.split("="));
            author.forEach(item -> c3.orEqualTo("author", item));
            e.and(c3);
        }
        List<String> country = null;
        List<String> a = Arrays.asList(new Const().bookCountryArray);
        if (selectCountry != null && !selectCountry.equals("")) {//如果限制语种，则添加限制语种
            Example.Criteria c4 = e.createCriteria();
            country = Arrays.asList(selectCountry.split("="));
            country.forEach(item -> c4.orEqualTo("bookCountry", a.indexOf(item)));
            e.and(c4);
        }
        PageHelper.startPage(pageNo, pageSize, orderBy);
        List<BookInfo> bookInfos = bookInfoMapper.selectByExample(e);
        Example example = new Example(BookInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(search)) {
            criteria.orLike("bookName", "%" + search + "%").orLike("author", "%" + search + "%");
        }
        List<BookInfo> bookAll = bookInfoMapper.selectByExample(example);
        Set<String> years = new HashSet();
        Set<String> authors = new HashSet();
        Set<String> countries = new HashSet();
        bookAll.forEach(item -> {//将查询到的所有年、作者、语种信息添加到set集合中，返回页面作为限制条件
            years.add(String.valueOf(item.getPublishDate().getYear() + 1900));
            authors.add(item.getAuthor());
            countries.add(new Const().getBookCountryName(item.getBookCountry()));
        });
        PageInfo<BookInfo> page = new PageInfo<>(bookInfos);
        model.addAttribute("pageNo", pageNo);//页码
        model.addAttribute("page", page);//页码信息
        model.addAttribute("bookInfos", bookInfos);//当前页面结果
        model.addAttribute("orderBy", orderBy);//排序条件
        model.addAttribute("select", select);//排序编号
        model.addAttribute("search", search);//查找字符串
        model.addAttribute("years", years);//存放年
        model.addAttribute("authors", authors);//存放作者
        model.addAttribute("countries", countries);//存放语种
        model.addAttribute("selectYear", selectYear);//存放选择的年份
        model.addAttribute("selectAuthor", selectAuthor);//存放选择的作者
        model.addAttribute("selectCountry", selectCountry);//存放选择的语种
        model.addAttribute("const", new Const());
        if (returnPage == 1) {
            return "show";
        } else {
            return "admin/allBook";
        }
    }

    @GetMapping("rank")
    public String rank(@RequestParam(defaultValue = "2") Integer orderBy, Model model) {
        if (orderBy == 2) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            setTime(cal);
            Date startDate = cal.getTime();
            cal.set(Calendar.DATE, cal.get(cal.DATE) + 7);
            setTime(cal);
            Date finalDate = cal.getTime();
            List<BookRankVO> weakBook = bookBorrowService.getRank(startDate, finalDate);
            model.addAttribute("BookOrder", weakBook);
            model.addAttribute("updateTime", new Date());
            model.addAttribute("orderBy", 2);
        } else if (orderBy == 1) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DATE, new Date().getDate() + -1);
            Date startTime = cal.getTime();
            startTime.setHours(23);
            startTime.setMinutes(59);
            startTime.setSeconds(59);
            Date finalTime = new Date();
            finalTime.setHours(23);
            finalTime.setMinutes(59);
            finalTime.setSeconds(59);
            List<BookRankVO> dayBook = bookBorrowService.getRank(startTime, finalTime);
            model.addAttribute("BookOrder", dayBook);
            model.addAttribute("updateTime", new Date());
            model.addAttribute("orderBy", 1);
        } else {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            Date startDate = cal.getTime();
            Calendar cal1 = Calendar.getInstance();
            cal1.set(Calendar.DAY_OF_MONTH, 0);
            Date finalDate = cal1.getTime();
            List<BookRankVO> monthBook = bookBorrowService.getRank(startDate, finalDate);
            model.addAttribute("BookOrder", monthBook);
            model.addAttribute("updateTime", finalDate);
            model.addAttribute("orderBy", 3);
        }
        return "rank";
    }

    private void setTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
    }

    @GetMapping("bookInfo")
    public String getBook(Integer bookId, HttpSession session, Model model) {
        ReaderInfo readerInfo = (ReaderInfo) session.getAttribute("user");
        boolean exist;
        if (readerInfo != null) {
            exist = favouriteService.findFavouriteExist(readerInfo.getUid(), bookId);
        } else {
            exist = false;
        }
        BookInfo book = bookInfoMapper.selectByPrimaryKey(bookId);
        model.addAttribute("isExist", exist);
        model.addAttribute("book", book);
        model.addAttribute("const", new Const());//获取语种编号对应语种
        return "book";
    }

    @ResponseBody
    @PostMapping("addFavour")
    public ResponseResult<Void> addFavour(Integer userId, Integer bookId) {
        ResponseResult<Void> responseResult;
        if (favouriteService.addFavourite(userId, bookId) == 1) {
            responseResult = new ResponseResult<Void>(1, "收藏成功");
        } else {
            responseResult = new ResponseResult<Void>(0, "收藏失败");
        }
        return responseResult;
    }

    @ResponseBody
    @PostMapping("deleteFavour")
    public ResponseResult<Void> deleteFavour(Integer userId, Integer bookId) {
        ResponseResult<Void> responseResult;
        if (favouriteService.deleteFavourite(userId, bookId) == 1) {
            responseResult = new ResponseResult<Void>(1, "已取消收藏");
        } else {
            responseResult = new ResponseResult<Void>(0, "取消失败");
        }
        return responseResult;
    }

    @ResponseBody
    @PostMapping("findGetDate")
    public ResponseResult<Integer> findGetDate(Integer userId, Integer bookId) {
        ResponseResult<Integer> responseResult;
        if (bookBorrowService.hasBook(userId, bookId)) {
            responseResult = new ResponseResult<Integer>(1, "您已借阅了该书籍");
        } else if (bookBorrowService.notReturn(userId)) {
            responseResult = new ResponseResult<Integer>(1, "您还有过期未归还的图书");
        } else if (bookPredeterService.predetorTooMuch(bookId)) {
            responseResult = new ResponseResult<Integer>(1, "该书当前预定人数过多");
        } else {
            Integer orderNo = bookPredeterService.getMaxOrderNo(bookId) + 1;            //获取排序号
            Date recentReturn = bookPredeterService.findRecentReturn(bookId, orderNo - 1);//获取最大排序号对应最近的归还时间
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            responseResult = new ResponseResult<Integer>(0, "预计" + formatter.format(recentReturn) + "预定成功,是否预定", orderNo);
        }
        return responseResult;
    }

    @ResponseBody
    @PostMapping("addPredeter")
    public ResponseResult<Void> addPredeter(Integer bookId, Integer userId, Integer orderNo) {
        try {
            bookPredeterService.addPredeter(bookId, userId, orderNo);
        } catch (RestNumNotNullException | FineNotPaidException | AlreadyPredeterException exception) {
            return new ResponseResult<Void>(0, exception.getMessage());
        }
        return new ResponseResult<Void>(1, "预定成功！");
    }

    @GetMapping("showAllBooks")
    public String showAllBooks(Model model,
                               @RequestParam(required = false) String search, //@PathVariable相当于从http请求里获取这个属性并传入这个参数并作为路径的一部分
                               @RequestParam(defaultValue = "1") Integer pageNo,             //页码
                               @RequestParam(defaultValue = "2") Integer pageSize          //一页显示数量
    ) {
        List<BookInfo> bookInfos;
        if (search != null) {
            Example e = new Example(BookInfo.class);
            Example.Criteria c = e.createCriteria();
            c.orLike("bookName", "%" + search + "%").orLike("author", "%" + search + "%");
            PageHelper.startPage(pageNo, pageSize);
            bookInfos = bookInfoMapper.selectByExample(e);
        } else {
            PageHelper.startPage(pageNo, pageSize);
            bookInfos = bookInfoMapper.selectAll();
        }
        PageInfo<BookInfo> page = new PageInfo<>(bookInfos);
        model.addAttribute("bookInfos", bookInfos);
        model.addAttribute("const", new Const());
        model.addAttribute("page", page);
        model.addAttribute("pageNo", pageNo);
        return "admin/allBook";
    }

    @GetMapping("ToBorrow")
    public String toBorrow() {
        return "admin/toBorrow";
    }

    @GetMapping("borrowAgain")
    public String borrowAgain() {
        return "admin/borrowAgain";
    }

    @GetMapping("returnBook")
    public String returnBook() {
        return "admin/returnBook";
    }

    @RequestMapping("deleteBook")
    public String deleteBook(){return "admin/deleteBook";}

    @GetMapping("addBook")
    public String addBook() {
        return "admin/addBook";
    }

    @GetMapping("deleteFinal")
    public String deleteFinal(Model model){
        List<BookBackUp> bookBackUps = bookBackUpMapper.selectAll();
        model.addAttribute("bookBackUps",bookBackUps);
        model.addAttribute("const",new Const());
        return "admin/deleteFinal";
    }

    @ResponseBody
    @PostMapping("deleteFinal")
    public ResponseResult<Void> deleteFinal(Integer bookId){
        if( bookBackUpMapper.deleteByPrimaryKey(bookId)==0){
            return new ResponseResult<Void>(0,"删除失败");
        }else{
            return new ResponseResult<Void>(1,"已彻底删除");
        }
    }
    @GetMapping("restore")
    public String restore(Model model){
        List<BookBackUp> bookBackUps = bookBackUpMapper.selectAll();
        model.addAttribute("bookBackUps",bookBackUps);
        model.addAttribute("const",new Const());
        return "admin/restore";
    }

    @ResponseBody
    @PostMapping("restoreBook")
    public ResponseResult<Void> restoreBook(Integer bookId){
        BookBackUp bookInfo = bookBackUpMapper.selectByPrimaryKey(bookId);
        BookInfo bookBackUp = new BookInfo();
        bookBackUp.setRestNum(bookInfo.getRestNum());
        bookBackUp.setBorrowNum(bookInfo.getBorrowNum());
        bookBackUp.setMaxNum(bookInfo.getMaxNum());
        bookBackUp.setAuthor(bookInfo.getAuthor());
        bookBackUp.setBookCountry(bookInfo.getBookCountry());
        bookBackUp.setBookLoc(bookInfo.getBookLoc());
        bookBackUp.setBookName(bookInfo.getBookName());
        bookBackUp.setInfo(bookInfo.getInfo());
        bookBackUp.setISBN(bookInfo.getISBN());
        bookBackUp.setBookId(bookInfo.getBookId());
        bookBackUp.setBookImage(bookInfo.getBookImage());
        bookBackUp.setPublishDate(bookInfo.getPublishDate());
        bookBackUp.setIsOffprint(bookInfo.getIsOffprint());
        bookBackUp.setSeriesNum(bookInfo.getSeriesNum());
        bookBackUp.setPrice(bookInfo.getPrice());
        bookBackUp.setOrderNum(bookInfo.getOrderNum());
        bookBackUp.setBookType(bookInfo.getBookType());
        bookBackUp.setPublish(bookInfo.getPublish());
            try {
                bookBackUpMapper.delete(bookInfo);
                bookInfoMapper.insert(bookBackUp);
            }catch (Exception e){
                return new ResponseResult<Void>(0,"还原失败");
            }
            return new ResponseResult<Void>(1,"成功还原");
    }

    @RequestMapping("updateBook")
    public String updateBook(@RequestParam(required = false) Integer bookId, Model model) {
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookId);
        model.addAttribute("bookInfo", bookInfo);
        model.addAttribute("const", new Const());
        if (bookId != null && bookInfo == null) {
            model.addAttribute("message", "未查找到该图书信息");
        }
        return "admin/updateBook";
    }

    @ResponseBody
    @PostMapping("borrowBook")
    public ResponseResult<Void> borrowBook(Integer userId, Integer bookId) {
        if (userId == null || bookId == null) {
            return new ResponseResult<Void>(0, "用户id，图书id不能为空");
        }
        int state;
        try {
            state = bookBorrowService.borrow(userId, bookId);
        } catch (UserNotFoundException | BookNotFoundException | FineNotPaidException | BookNotRestException | OutOfPowerException | HasBorrowedException | CashNotPaidException exception) {
            return new ResponseResult<Void>(0, exception.getMessage());
        }
        if (state == 1) {
            return new ResponseResult<Void>(1, "借阅成功");
        } else {
            return new ResponseResult<Void>(1, "您已借阅预定书籍");
        }
    }

    @ResponseBody
    @PostMapping("borrowBookAgain")
    public ResponseResult<Void> borrowBookAgain(Integer userId, Integer bookId) {
        if (userId == null || bookId == null) {
            return new ResponseResult<Void>(0, "用户id，图书id不能为空");
        }
        try {
            bookBorrowService.borrowAgain(userId, bookId);
        } catch (BorrowNotFoundException | FineNotPaidException | HasBorrowedAgainException | HasPredeterException | NotAllowedBorrowAgainException | CashNotPaidException exception) {
            return new ResponseResult<Void>(0, exception.getMessage());
        }
        return new ResponseResult<Void>(1, "续借成功");
    }

    @ResponseBody
    @PostMapping("checkFine")
    public ResponseResult<Void> checkFine(Integer userId, Integer bookId, Integer isLost) {
        if (userId == null || bookId == null) {
            return new ResponseResult<Void>(0, "用户id，图书id不能为空");
        }
        Example e = new Example(BookBorrow.class);
        Example.Criteria criteria = e.createCriteria();
        criteria.andEqualTo("uid", userId).andEqualTo("bookId", bookId).andIsNull("returnDate");
        if (bookBorrowMapper.selectOneByExample(e) == null) {
            return new ResponseResult<Void>(-1, "未查询到该借阅信息");
        }
        if (isLost == 1) {
            return new ResponseResult<Void>(0, "请缴纳罚金:" + bookInfoMapper.selectByPrimaryKey(bookId).getPrice() + "元");
        }
        BigDecimal fine = bookBorrowService.checkFine(userId, bookId);
        if (!fine.equals(new BigDecimal(0))) {
            return new ResponseResult<Void>(0, "请缴纳罚金" + fine + "元");
        }
        return new ResponseResult<Void>(1, "没有罚金");
    }

    @ResponseBody
    @PostMapping("toReturnBook")
    public ResponseResult<Void> toReturnBook(Integer userId, Integer bookId, @RequestParam(defaultValue = "0") Integer isLost) {
        bookBorrowService.returnBook(userId, bookId, isLost);
        return new ResponseResult<Void>(1, "归还成功");
    }

    @ResponseBody
    @PostMapping("findBySearchType")
    public ResponseResult<List<BookInfo>> findBySearchType(@RequestParam(required = false) String search,
                                                           @RequestParam(required = false) Integer searchType, Model model,
                                                           @RequestParam(defaultValue = "1") Integer pageNo,             //页码
                                                           @RequestParam(defaultValue = "3") Integer pageSize)          //一页显示数量)
    {
        Example e = new Example(BookInfo.class);
        Example.Criteria c = e.createCriteria();
        if (searchType == 1) {
            c.andLike("bookName", "%" + search + "%");
        } else if (searchType == 2) {
            c.andEqualTo("ISBN", search);
        } else {
            c.andEqualTo("author", search);
        }
        PageHelper.startPage(pageNo, pageSize);
        List<BookInfo> bookInfos = bookInfoMapper.selectByExample(e);
        PageInfo<BookInfo> page = new PageInfo<>(bookInfos);
        if (bookInfos.size() == 0) {
            return new ResponseResult<List<BookInfo>>(0, "未查询到信息", null);
        }
        model.addAttribute("bookInfos", bookInfos);
        model.addAttribute("page", page);
        model.addAttribute("pageNo", pageNo);
        return new ResponseResult<List<BookInfo>>(1, "查询成功", bookInfos);
    }

    @PostMapping("toAddBook")
    public String toAddBook(String isbn, String bookName, Integer bookLoc, String bookType, String author, Integer bookCountry, Model model,
                            Integer isOffprint, @RequestParam(defaultValue = "1") Integer orderNum, @RequestParam(defaultValue = "-1") Integer seriesNum, String publish, String publishDate,
                            BigDecimal price, Integer maxNum, @RequestParam(required = false) String info,
                            @RequestParam(value = "file", required = false) MultipartFile multipartFile
    ) throws ParseException {
        String message = null;
        if (isbn.length() < 10 || isbn.length() > 13) {
            message = "请输入正确的isbn编号";
        } else if (orderNum > seriesNum) {
            message = "请输入正确的系列号";
        } else if (isOffprint == 0 && seriesNum==-1) {
            message = "请输入系列书的总册数";
        } else {
            if (isOffprint == 1) {
                orderNum = 1;
                seriesNum = null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date publishDat = sdf.parse(publishDate);
            String path = null;
            String FileName = null;
            String hashPath = null;
            if (!multipartFile.isEmpty()) {
                String filename = multipartFile.getOriginalFilename();
                //获取上传目标的路径，可以是某个ftp虚拟主机下的路径，服务器的路径
                String realPath = "E:/j2ee-chl/book-manager/src/main/resources/static/img/";
                hashPath = getHashPath(filename);
                path = realPath + hashPath;
                System.out.println(path);
                File file = new File(path);//按照文件名的hashcode值分文件夹存储，提升检索和查询速度
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileName = gerRandomName(filename);
                File targetFile = new File(file, FileName);
                try {
                    multipartFile.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            BookInfo bookInfo = new BookInfo(null, bookCountry, bookType, bookLoc, isbn, bookName, author, isOffprint, orderNum, seriesNum, publish, publishDat, price, maxNum, 0, maxNum, info, FileName == null ? null : ("/img/" + hashPath + "/" + FileName));
            bookInfoMapper.insert(bookInfo);
        }
        model.addAttribute("message", message);
        return "admin/addBook";
    }

    @ResponseBody
    @PostMapping("checkType")
    public ResponseResult<Void> checkType(String bookType) {
        if (bookTypeMapper.selectByPrimaryKey(bookType) == null) {
            return new ResponseResult<Void>(0, "请输入正确的分类编号");
        } else {
            return new ResponseResult<Void>(1, "分类编号正确");
        }
    }

    @ResponseBody
    @PostMapping("toUpdateBook")
    public ResponseResult<Void> toUpdateBook(Integer bookId, String isbn, String bookName, Integer bookCountry, String bookType, Integer bookLoc, String author, Integer isOffprint, Integer orderNum,@RequestParam(defaultValue = "-1") Integer seriesNum, String publish,String publishDate, BigDecimal price, Integer maxNum) throws ParseException {
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookId);
        if((bookInfo.getMaxNum()-maxNum)>bookInfo.getRestNum()){//图书总库存减少数量大于剩余数量报错，
            return new ResponseResult<Void>(0,"减少的库存数量不得少于剩余的库存数量");
        }
        if(isOffprint==0&&seriesNum==-1){
            return new ResponseResult<Void>(0,"请输入系列书的系列总册数");
        }
        BookInfo bookInfo1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date publishDat = sdf.parse(publishDate);
        if (bookInfo.getMaxNum() < maxNum && bookInfo.getMaxNum() == 0) {//原先库存为0且有预定的情况
            Example example = new Example(BookPredeter.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("bookId", bookId);
            List<BookPredeter> bookPredeters = bookPredeterMapper.selectByExample(example);
            int num = 0, restNum = 0;
            if (maxNum < bookPredeters.size()) {//修改数量小于预定数
                num = maxNum;
                bookInfo1 = new BookInfo(bookId, bookCountry, bookType, bookLoc, isbn, bookName, author, isOffprint, orderNum, seriesNum, publish, publishDat, price, maxNum, maxNum, 0, bookInfo.getInfo(), bookInfo.getBookImage());
            } else {
                num = bookPredeters.size();
                restNum = maxNum - bookPredeters.size();
                bookInfo1 = new BookInfo(bookId, bookCountry, bookType, bookLoc, isbn, bookName, author, isOffprint, orderNum, seriesNum, publish, publishDat, price, maxNum, num + bookInfo.getBorrowNum(), restNum, bookInfo.getInfo(), bookInfo.getBookImage());
            }
            List<BookPredeter> bookPredeters1 = bookPredeterMapper.selectByOrder(bookId, num);
            bookPredeters1.forEach(item -> {
                item.setBackDate(new Date());
                bookPredeterMapper.updateByPrimaryKey(item);
            });
        }
        bookInfo1 = new BookInfo(bookId, bookCountry, bookType, bookLoc, isbn, bookName, author, isOffprint, orderNum, seriesNum, publish, publishDat, price, maxNum, bookInfo.getBorrowNum(), maxNum - bookInfo.getBorrowNum(), bookInfo.getInfo(), bookInfo.getBookImage());
            bookInfoMapper.updateByPrimaryKey(bookInfo1);
            return new ResponseResult<Void>(1,"修改成功");
        }
    @ResponseBody
    @PostMapping("toDeleteBook")
    public ResponseResult<Void> toDeleteBook(Integer bookId){
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookId);
        if(bookInfo==null){
            return new ResponseResult<Void>(0,"未查询到该图书信息");
        }
        BookBackUp bookBackUp = new BookBackUp();
        bookBackUp.setRestNum(bookInfo.getRestNum());
        bookBackUp.setBorrowNum(bookInfo.getBorrowNum());
        bookBackUp.setMaxNum(bookInfo.getMaxNum());
        bookBackUp.setAuthor(bookInfo.getAuthor());
        bookBackUp.setBookCountry(bookInfo.getBookCountry());
        bookBackUp.setBookLoc(bookInfo.getBookLoc());
        bookBackUp.setBookName(bookInfo.getBookName());
        bookBackUp.setInfo(bookInfo.getInfo());
        bookBackUp.setISBN(bookInfo.getISBN());
        bookBackUp.setBookId(bookInfo.getBookId());
        bookBackUp.setBookImage(bookInfo.getBookImage());
        bookBackUp.setPublishDate(bookInfo.getPublishDate());
        bookBackUp.setIsOffprint(bookInfo.getIsOffprint());
        bookBackUp.setSeriesNum(bookInfo.getSeriesNum());
        bookBackUp.setPrice(bookInfo.getPrice());
        bookBackUp.setOrderNum(bookInfo.getOrderNum());
        bookBackUp.setBookType(bookInfo.getBookType());
        bookBackUp.setPublish(bookInfo.getPublish());
        try {
            bookBackUpMapper.insert(bookBackUp);
            bookInfoMapper.delete(bookInfo);
        }catch (Exception e){
            return new ResponseResult<Void>(0,"删除失败");
        }
        return new ResponseResult<Void>(1,"删除成功,该图书将只支持归还功能，如果想彻底删除请在'彻底删除'中删除该图书，或者在'还原'中还原");
    }

    @ResponseBody
    @PostMapping("toDeleteFinal")
    public ResponseResult<Void> toDeleteFinal(Integer bookId){
        try {
            bookBackUpMapper.deleteByPrimaryKey(bookId);
        }catch (Exception e){
            return new ResponseResult<Void>(0,"彻底删除失败");
        }
        return new ResponseResult<Void>(1,"彻底删除成功");
    }
    //获取随机文件名称
    private String gerRandomName(String fileName) {
        return UUID.randomUUID() + "-" + fileName;
    }

    //分散存储文件的操作(节省搜索时间)
    private String getHashPath(String fileName) {
        //获取文件的hashCode值
        int hashCode = fileName.hashCode();
        int path1 = hashCode & 0xf;//获取比特后4位
        int path2 = hashCode & 0xf0 >> 4;//获取比特5-8位
        return String.valueOf(path1) + "/" + String.valueOf(path2);
    }
}
