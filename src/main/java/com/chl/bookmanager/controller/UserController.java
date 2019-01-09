package com.chl.bookmanager.controller;

import com.chl.bookmanager.common.Const;
import com.chl.bookmanager.domain.*;
import com.chl.bookmanager.exception.*;
import com.chl.bookmanager.mapper.BookInfoMapper;
import com.chl.bookmanager.mapper.BookPredeterMapper;
import com.chl.bookmanager.mapper.ReaderInfoMapper;
import com.chl.bookmanager.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author:Administrator
 * @date:2018/12/26/026
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Value("${md5.salt}")
    private String salt;
    @Autowired
    ReaderInfoService readerInfoService;
    @Autowired
    SchoolMemberService schoolMemberService;
    @Autowired
    FavouriteService favouriteService;
    @Autowired
    BookInfoMapper bookInfoMapper;
    @Autowired
    ReaderInfoMapper readerInfoMapper;
    @Autowired
    PredeterService predeterService;
    @Autowired
    BookPredeterMapper bookPredeterMapper;
    @Autowired
    BookBorrowService bookBorrowService;
    //根据用户名密码检验是否存在，存在则返回状态1和登录成功信息，不存在抛出异常信息，返回弹窗
    @PostMapping("doLogin")
    @ResponseBody
    public ResponseResult<Void> login(String username, String password, HttpSession session) {
        ResponseResult<Void> responseResult;
        try {
            ReaderInfo user = readerInfoService.login(username, password);
            session.setAttribute("user", user);
            responseResult = new ResponseResult<Void>(1, "登录成功");
        } catch (UserNotFoundException e) {
            responseResult = new ResponseResult<Void>(0, e.getMessage());
        } catch (PasswordNotMatchException e) {
            responseResult = new ResponseResult<Void>(-1, e.getMessage());
        }
        return responseResult;
    }
//    将注册表信息注册到数据库中
    @PostMapping("doRegister")
    @ResponseBody
    public ResponseResult<Void> register(String username,String password,Integer sex,Integer userType,String memberId,String phone) {
            ResponseResult<Void> responseResult;
            ReaderInfo readerInfo;
            int year = 0,isPeriod = 0;
            if(userType == 1){
                year = 4;
                isPeriod = 1;
            } else if(userType == 6){
                year = 2;
                isPeriod = 1;
            }
            Date date = new Date();
            date.setYear(date.getYear()+year);
        if(userType==1||userType==6) {
            readerInfo=new ReaderInfo(null, username, password, phone, null, sex, userType, phone, new BigDecimal(0),new Date() , isPeriod,date,0 );
        }else {
            readerInfo=new ReaderInfo(null, username, password, phone, null, sex, userType, phone, new BigDecimal(0), null, isPeriod,null,0 );
        }
        if (readerInfoService.register(readerInfo,memberId)==1) {
            responseResult = new ResponseResult<Void>(1, "注册成功");
        }else {
            responseResult = new ResponseResult<Void>(0, "注册失败");
        }
        return responseResult;
        }
    //根据username如果已存在信息已存在用户已存在状态0，不存在返回账户可用信息，状态1
    @GetMapping("checkUsername")
    @ResponseBody
    public ResponseResult<Void> checkUsername(String username){
        ResponseResult<Void> responseResult;
        if(readerInfoService.checkUsernameHasExist(username)) {
            responseResult = new ResponseResult<Void>(0, "用户已存在");
        }else {
            responseResult = new ResponseResult<Void>(1,"用户可使用");
        }
        return responseResult;
    }
//      根据memberId和userType查找是否存在且没使用的，不存在或已使用返回信息返回状态0，存在且没使用返回状态1信息有效
     @GetMapping("checkMemberId")
     @ResponseBody
    public ResponseResult<Void> checkMemberId(String memberId,Integer userType){
         System.out.println();
        ResponseResult<Void> responseResult;
        if(schoolMemberService.checkMemberIdHasExist(memberId,userType)){
            responseResult = new ResponseResult<Void>(1, "成员编号可使用");
        }else {
            responseResult = new ResponseResult<Void>(0,"成员编号不存在或已使用");
        }
         return responseResult;
     }
    //根据phone判断是否已存在，已存在返回状态0返回错误信息电话已注册，不存在返回状态1，信息可使用
    @GetMapping("checkPhone")
    @ResponseBody
    public ResponseResult<Void> checkPhone(String phone){
        ResponseResult<Void> responseResult;
        if(readerInfoService.checkPhoneHaxExist(phone)){
            responseResult = new ResponseResult<Void>(0,"电话号已注册");
        }else {
            responseResult = new ResponseResult<Void>(1,"电话号可以使用");
        }
        return responseResult;
    }
    @RequestMapping("logout")
    public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
        session.removeAttribute("user");
        response.sendRedirect(request.getHeader("REFERER"));
    }

    @GetMapping("myBorrow")
    public String myBorrow(HttpSession session, Model model){
        ReaderInfo readerInfo = (ReaderInfo) session.getAttribute("user");
        List<BookBorrowVO> bookBorrowVo = readerInfoService.findBorrow(readerInfo.getUid());
        model.addAttribute("bookBorrow",bookBorrowVo);
        return "myBorrow";
    }
    @GetMapping("myFavourite")
    public String myFavourite(HttpSession session,Model model){
        ReaderInfo readerInfo = (ReaderInfo) session.getAttribute("user");
        List<BookInfo> bookInfo = favouriteService.findFavourite(readerInfo.getUid());
        model.addAttribute("bookInfos",bookInfo);
        return "myFavourite";
    }
    @GetMapping("changePassword")
    public String changePassword(){
        return "changePassword";
    }
    @ResponseBody
    @PostMapping("toChange")
    public ResponseResult<Void> toChange(String userName,String oldPassword,String newPassword,String conformPassword,HttpSession session){
        ResponseResult<Void> responseResult;
        if(newPassword.length()<3||newPassword.length()>15){
            responseResult = new ResponseResult<Void>(0,"密码为3-15位");
        }else if(!newPassword.equals(conformPassword)){
            responseResult = new ResponseResult<Void>(0,"两次密码输入不一致");
        }else if (!readerInfoService.checkPassword(userName,DigestUtils.md5Hex(oldPassword+salt))){
            responseResult = new ResponseResult<Void>(0,"密码输入错误");
        }else{
            readerInfoService.changePassword(userName,DigestUtils.md5Hex(newPassword+salt));
            responseResult = new ResponseResult<Void>(1,"成功修改密码");
            session.removeAttribute("user");
        }
        return responseResult;
    }

    @GetMapping("showAllUsers")
    public String showAllUsers(Model model,
                               @RequestParam(required = false) String search, //@PathVariable相当于从http请求里获取这个属性并传入这个参数并作为路径的一部分
                               @RequestParam(defaultValue = "1") Integer pageNo,             //页码
                               @RequestParam(defaultValue = "3") Integer pageSize          //一页显示数量
    ){
        List<ReaderInfo> readerInfos;
        PageHelper.startPage(pageNo, pageSize);
        readerInfos = readerInfoService.showAllUsers();
        PageInfo<ReaderInfo> page = new PageInfo<>(readerInfos);
        model.addAttribute("const",new Const());
        model.addAttribute("readerInfos",readerInfos);
        model.addAttribute("page",page);
        model.addAttribute("pageNo",pageNo);
        return "admin/allUser";
    }
    @GetMapping("myPredeter")
    public String myPredeter(HttpSession session,Model model){
        System.out.println(1);
        ReaderInfo readerInfo = (ReaderInfo) session.getAttribute("user");
        List<BookPredeter> predeter = predeterService.findPredeter(readerInfo.getUid());
        List<BookPredeterVO> list=new ArrayList<BookPredeterVO>();
        predeter.forEach(item->{
            BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(item.getBookId());
            System.out.println(bookInfo.toString());
            list.add(new BookPredeterVO(item.getPredeterId(),item.getUid(),item.getBookId(),item.getBackDate(),item.getOrderNo(),bookInfo.getISBN(),bookInfo.getBookName()));
        });
        model.addAttribute("predeter",list);
        return "myPredeter";
    }
    @GetMapping("cancelPredeter")
    public String cancelPredeter(Integer bookId,HttpSession session){
        ReaderInfo user = (ReaderInfo) session.getAttribute("user");
        Example e =new Example(BookPredeter.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("uid",user.getUid()).andEqualTo("bookId",bookId);
        BookPredeter bookPredeter = bookPredeterMapper.selectOneByExample(e);
        if(bookPredeter.getBackDate()!=null){//已经预定成功，则执行一次归还操作
            bookBorrowService.returnBook(user.getUid(), bookId, 0);
        }
        bookPredeterMapper.deleteByExample(e);
        return "redirect:/user/myPredeter";
    }
    @GetMapping("report")
    public String report(){return "admin/report";}
    @ResponseBody
    @PostMapping("doReport")
    public ResponseResult<Void> doReport(Integer uid){
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(uid);
        if(readerInfo==null||readerInfo.getStatus()!=0){
            return new ResponseResult<Void>(0,"未查询到该用户名或用户状态不正常");
        }
        readerInfo.setStatus(2);
        readerInfoMapper.updateByPrimaryKey(readerInfo);
        return new ResponseResult<Void>(1,"用户"+readerInfo.getUsername()+"已挂失");
    }
    @GetMapping("open")
    public String open(){return "admin/open";}
    @ResponseBody
    @PostMapping("doOpen")
    public ResponseResult<Void> doOpen(Integer uid){
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(uid);
        if(readerInfo==null||readerInfo.getStatus()!=2){
            return new ResponseResult<Void>(0,"未查询到该挂失用户名或用户状态不正常");
        }
        readerInfo.setStatus(0);
        readerInfoMapper.updateByPrimaryKey(readerInfo);
        return new ResponseResult<Void>(1,"用户"+readerInfo.getUsername()+"已解禁");
    }
    @GetMapping("rebuild")
    public String rebuild(){return "admin/rebuild";}
    @ResponseBody
    @PostMapping("doRebuild")
    public ResponseResult<Void> doRebuild(Integer uid){
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(uid);
        if(readerInfo==null||readerInfo.getStatus()!=2){
            return new ResponseResult<Void>(0,"未查询到该挂失用户名或用户状态不正常");
        }
        readerInfoService.rebuild(uid);
        return new ResponseResult<Void>(1,"用户"+readerInfo.getUsername()+"已重办，原用户id将不再可用");
    }
    @RequestMapping("updateUser")
    public String updateUser(@RequestParam(required = false)Integer userId,Model model){
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(userId);
        model.addAttribute("readerInfo", readerInfo);
        model.addAttribute("const", new Const());
        if (userId != null && readerInfo == null) {
            model.addAttribute("message", "未查找到该读者信息");
        }
        return "admin/updateUser";
    }
    @ResponseBody
    @PostMapping("toUpdateReader")
    public ResponseResult<Void> toUpdateReader(Integer readerId,String username,String readername,Integer sex,String phone,BigDecimal cashPledge,Integer isPeriod,String periodValidity,Integer status) throws ParseException {
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(readerId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date periodValidity1 = sdf.parse(periodValidity);
        ReaderInfo readerInfo1 = new ReaderInfo(readerId, username, readerInfo.getPassword(), readername, readerInfo.getImg(), sex, readerInfo.getTypeId(), phone, cashPledge, readerInfo.getRegDate(), isPeriod, periodValidity1, status);
        try {
            readerInfoMapper.updateByPrimaryKey(readerInfo1);
        }catch (Exception e ){
            return new ResponseResult<Void>(0,"修改失败");
        }
        return new ResponseResult<Void>(1,"修改成功");
    }
    @RequestMapping("deleteUser")
    public String deleteUser(){
        return "/admin/deleteUser";
    }
    @ResponseBody
    @PostMapping("toDeleteUser")
    public ResponseResult<Void> toDeleteUser(Integer userId){
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(userId);
        if(readerInfo==null||readerInfo.getStatus()!=0){
            return new ResponseResult<Void>(0,"未找到相关读者信息");
        }
        readerInfo.setStatus(3);
        try {
            readerInfoMapper.updateByPrimaryKey(readerInfo);
        }catch (Exception e){
            return new ResponseResult<Void>(0,"注销失败");
        }
        return new ResponseResult<Void>(1,"注销成功");
    }
}
