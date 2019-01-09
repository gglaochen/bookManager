package com.chl.bookmanager.service.impl;

import com.chl.bookmanager.domain.*;
import com.chl.bookmanager.exception.*;
import com.chl.bookmanager.mapper.*;
import com.chl.bookmanager.service.BookBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author:Administrator
 * @date:2019/1/1/001
 */
@Service
public class BookBorrowServiceImpl implements BookBorrowService {
    @Autowired
    private BookBorrowMapper bookBorrowMapper;
    @Autowired
    private ReaderInfoMapper readerInfoMapper;
    @Autowired
    private BookPredeterMapper bookPredeterMapper;
    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Autowired
    private UserTypeMapper userTypeMapper;
    @Autowired
    private BookBackUpMapper bookBackUpMapper;

    @Override
    public List<BookRankVO> getRank(Date startDate, Date finalDate) {
        List<BookInfo> bookInfos = bookBorrowMapper.getRank(startDate, finalDate);
        List<BookRankVO> bookRankVOS = new ArrayList<BookRankVO>();
        bookInfos.forEach(item-> {
            Integer count = bookBorrowMapper.selectBorrowCount(item.getBookId(), startDate, finalDate);
            if(count==null){
                count=0;
            }
            bookRankVOS.add(new BookRankVO(item.getBookName(),item.getAuthor(),count));

        });
        return bookRankVOS;
    }

    @Override
    public boolean hasBook(Integer userId, Integer bookId) {
        Example example =new Example(BookBorrow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookId",bookId).andEqualTo("uid",userId).andIsNull("returnDate");
        return bookBorrowMapper.selectByExample(example) == null;
    }

    @Override
    public boolean notReturn(Integer userId) {
        Example example =new Example(BookBorrow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("fine",0).andEqualTo("uid",userId).andEqualTo("isPaid",0);
        return bookBorrowMapper.selectByExample(example) == null;
    }

    @Override
    public int borrow(Integer userId, Integer bookId) {
        int state=0;
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(userId);
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookId);
        Example e = checkFinePaid(userId);
        Example e2 = new Example(BookBorrow.class);
        Example.Criteria c2 = e2.createCriteria();
        c2.andEqualTo("uid",userId);
        Example e3 = new Example(BookPredeter.class);
        Example.Criteria c3 = e3.createCriteria();
        c3.andEqualTo("uid",userId).andEqualTo("bookId",bookId);
        List<BookBorrow> bookInfos = bookBorrowMapper.selectByExample(e2);
        Example e4 = new Example(BookBorrow.class);
        Example.Criteria c4 =  e4.createCriteria();
        c4.andEqualTo("uid",userId).andEqualTo("bookId",bookId).andIsNull("returnDate");
        Example e5 = new Example(BookPredeter.class);
        Example.Criteria c5 = e5.createCriteria();
        c5.andEqualTo("bookId",bookId).andEqualTo("uid",userId);
        if(readerInfo==null||readerInfo.getStatus()!=0){
            throw new UserNotFoundException("用户名不存在或用户状态异常");
        }else if (bookInfo==null) {
            throw new BookNotFoundException("图书不存在");
        }else if(bookInfos.size()>=userTypeMapper.selectByPrimaryKey(readerInfoMapper.selectByPrimaryKey(userId).getTypeId()).getMaxBorrowNum()){
            throw new OutOfPowerException("您已经借了您可借图书的上限");
        }else if(bookInfoMapper.selectByPrimaryKey(bookId).getRestNum()==0&&bookPredeterMapper.selectByExample(e5).size()==0){
            throw new BookNotRestException("该书已经借完了，快去预定吧");
        }else if (readerInfoMapper.selectByPrimaryKey(userId).getCashPledge().equals(new BigDecimal(0))) {
            throw new CashNotPaidException("请先缴纳押金");
        }else if(bookBorrowMapper.selectOneByExample(e4)!=null) {
            throw new HasBorrowedException("您已经借阅了该书");
        }else if(bookBorrowMapper.selectByExample(e).size()!=0){
            throw new FineNotPaidException("用户还有未交罚金");
        }else if(bookPredeterMapper.selectByExample(e3).size()==0){//没有预定
            bookBorrowMapper.insert(new BookBorrow(null, userId, bookId, new Date(), null, 0, new BigDecimal(0), 0, 0));
            bookInfoMapper.updateBorrowOut(bookId);//借出数量+1，剩余数量-1
                    state=1;
        }else {//预定
            bookBorrowMapper.insert(new BookBorrow(null, userId, bookId, new Date(), null, 0, new BigDecimal(0), 0, 0));
            bookPredeterMapper.deleteByExample(e3);
        }
        return state;
    }
    //UserNotFoundException |BookNotFoundException|FineNotPaidException|HasPredeterException exception
    @Override
    public void borrowAgain(Integer userId, Integer bookId) {
        Example e2 = new Example(BookPredeter.class);
        Example.Criteria c2 = e2.createCriteria();
        c2.andEqualTo("bookId",bookId);
        Example e = checkFinePaid(userId);
        Example e3 = new Example(BookBorrow.class);
        Example.Criteria c3 = e3.createCriteria();
        c3.andEqualTo("uid",userId).andEqualTo("bookId",bookId);
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(userId);
        Example e4 = new Example(BookBorrow.class);
        Example.Criteria c4 = e4.createCriteria();
        c4.andEqualTo("uid",userId).andEqualTo("bookId",bookId).andIsNull("returnDate");
        if(bookBorrowMapper.selectOneByExample(e4)==null){
            throw new BorrowNotFoundException("未查询到该借阅信息");
        }else if(readerInfoMapper.selectByPrimaryKey(userId).getCashPledge().equals(new BigDecimal(0))){
            throw new CashNotPaidException("请先缴纳押金");
        }else if(bookBorrowMapper.selectByExample(e).size()!=0) {
            throw new FineNotPaidException("用户还有未交罚金");
        }else if(bookBorrowMapper.selectOneByExample(e3).getIsRenew()==1){
            throw new HasBorrowedAgainException("您已经续借过该书了");
        }else if (bookPredeterMapper.selectByExample(e2).size()!=0){
            throw new HasPredeterException("该书已有人预定，不可续借");
        }else if(bookBorrowMapper.AllowedBorrowAgain(userId,bookId)<0){
            throw new NotAllowedBorrowAgainException("您借阅的图书已经过期，请先缴纳罚金");
        }else if (bookBorrowMapper.AllowedBorrowAgain(userId,bookId)>5){
            throw new NotAllowedBorrowAgainException("图书只能在到期前5天内续借");
        }else{//修改续借为true
            BookBorrow bookBorrow = bookBorrowMapper.selectOneByExample(e4);
            bookBorrow.setIsRenew(1);
            bookBorrowMapper.updateByPrimaryKey(bookBorrow);
        }
    }

    @Override
    public BigDecimal checkFine(Integer userId, Integer bookId) {
        Example e =new Example(BookBorrow.class);
        Example.Criteria c =e.createCriteria();
        c.andEqualTo("uid",userId).andEqualTo("bookId",bookId);
        return bookBorrowMapper.selectOneByExample(e).getFine();
    }

    @Override
    public void returnBook(Integer userId, Integer bookId, Integer isLost) {
        Example e = new Example(BookBorrow.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("uid",userId).andEqualTo("bookId",bookId).andIsNull("returnDate");
        BookBorrow bookBorrow = bookBorrowMapper.selectOneByExample(e);
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookId);
        BookBackUp bookBackUp = bookBackUpMapper.selectByPrimaryKey(bookId);
        if(isLost==1){//丢失
            if(bookInfo!=null) {//图书表
                bookBorrow.setFine(bookInfo.getPrice());
                bookInfo.setMaxNum(bookInfo.getMaxNum()-1);
                bookInfo.setBorrowNum(bookInfo.getBorrowNum()-1);
            }else{//备份图书表
                bookBorrow.setFine(bookBackUp.getPrice());
                bookBackUp.setMaxNum(bookInfo.getMaxNum()-1);
                bookBackUp.setBorrowNum(bookInfo.getBorrowNum()-1);
            }
        }
        if(!bookBorrow.getFine().equals(new BigDecimal(0))){//有罚金则设置已支付
            bookBorrow.setIsPaid(1);
        } Example e2 = new Example(BookPredeter.class);
        Example.Criteria c2 = e2.createCriteria();
        c2.andEqualTo("bookId",bookId);
        List<BookPredeter> bookPredeters = bookPredeterMapper.selectByExample(e2);
        if(bookPredeters.size()!=0){//有预定，只修改预定表，不把归还记录返回给bookInfo
            bookPredeters.forEach(item->{
                if(item.getOrderNo()==1){//顺序号1修改顺序为-1，归还日期为当前日期
                    item.setOrderNo(-1);
                    item.setBackDate(new Date());
                    bookPredeterMapper.updateByPrimaryKey(item);
                    //预定成功消息
                }else if(item.getOrderNo()!=-1){//别的顺序号往前移1
                    item.setOrderNo(item.getOrderNo()-1);
                    bookPredeterMapper.updateByPrimaryKey(item);
                }
            });

        }else{//没有预定
            if(bookInfo!=null){
                bookInfo.setBorrowNum(bookInfo.getBorrowNum()-1);
                bookInfo.setRestNum(bookInfo.getRestNum()+1);
            }else {
                bookBackUp.setBorrowNum(bookBackUp.getBorrowNum()-1);
                bookBackUp.setRestNum(bookBackUp.getRestNum()+1);
            }
        }
        bookBorrow.setReturnDate(new Date());
        Example e3 = new Example(BookBorrow.class);
        Example.Criteria c3 = e3.createCriteria();
        c3.andEqualTo("uid",userId).andEqualTo("bookId",bookId);
        bookBorrowMapper.updateByExample(bookBorrow,e3);
        if(bookInfo!=null) {
            bookInfoMapper.updateByPrimaryKey(bookInfo);
        }else {
            bookBackUpMapper.updateByPrimaryKey(bookBackUp);
        }
    }

    private Example checkFinePaid(Integer userId) {
        Example e = new Example(BookBorrow.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("uid",userId);
        c.andNotEqualTo("fine",0);
        c.andEqualTo("isPaid",0);
        return e;
    }
}
