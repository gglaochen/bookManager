package com.chl.bookmanager.service.impl;

import com.chl.bookmanager.domain.BookBorrow;
import com.chl.bookmanager.domain.BookInfo;
import com.chl.bookmanager.domain.BookPredeter;
import com.chl.bookmanager.domain.ReaderInfo;
import com.chl.bookmanager.exception.AlreadyPredeterException;
import com.chl.bookmanager.exception.FineNotPaidException;
import com.chl.bookmanager.exception.RestNumNotNullException;
import com.chl.bookmanager.mapper.BookBorrowMapper;
import com.chl.bookmanager.mapper.BookInfoMapper;
import com.chl.bookmanager.mapper.BookPredeterMapper;
import com.chl.bookmanager.mapper.ReaderInfoMapper;
import com.chl.bookmanager.service.BookPredeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author:Administrator
 * @date:2019/1/2/002
 */
@Service
public class BookPredeterServiceImpl implements BookPredeterService {
    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Autowired
    private BookPredeterMapper bookPredeterMapper;
    @Autowired
    private BookBorrowMapper bookBorrowMapper;

    @Override
    public boolean predetorTooMuch(Integer bookId) {
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookId);
        Example example =new Example(BookPredeter.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookId",bookId).andEqualTo("orderNo",bookInfo.getMaxNum());
        return bookPredeterMapper.selectByExample(example) == null;
    }

    @Override
    public Integer getMaxOrderNo(Integer bookId) {
        Integer maxOrderNo=bookPredeterMapper.getMaxOrderNo(bookId);
         if (maxOrderNo==null){//没人预定
             return 0;
         }else if(maxOrderNo==-1){//预定的人已预定到
             return 0;
         }
         else return maxOrderNo;
    }

    @Override
    public Date findRecentReturn(Integer bookId,Integer orderNo) {
        return bookPredeterMapper.findRecentReturn(bookId,orderNo);
    }

    @Override
    public void addPredeter(Integer bookId, Integer userId, Integer orderNo) {
        Example example =new Example(BookPredeter.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uid",userId).andEqualTo("bookId",bookId);
        if(bookPredeterMapper.selectByExample(example).size()!=0){
            throw new AlreadyPredeterException("您已经预定过该书籍了");
        }
        if(bookInfoMapper.selectByPrimaryKey(bookId).getRestNum()!=0){
            throw new RestNumNotNullException("该图书尚还有库存");
        }
        Example e = new Example(BookBorrow.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("uid",userId).andNotEqualTo("fine",0).andEqualTo("isPaid",0);
        if(bookBorrowMapper.selectByExample(e).size()!=0){
            throw new FineNotPaidException("您还有未缴纳的罚金");
        }
        bookPredeterMapper.insert(new BookPredeter(null,userId,bookId,null,orderNo));
    }
}
