package com.chl.bookmanager;

import com.chl.bookmanager.domain.BookBorrow;
import com.chl.bookmanager.domain.BookInfo;
import com.chl.bookmanager.domain.BookPredeter;
import com.chl.bookmanager.domain.ReaderInfo;
import com.chl.bookmanager.mapper.BookBorrowMapper;
import com.chl.bookmanager.mapper.BookInfoMapper;
import com.chl.bookmanager.mapper.BookPredeterMapper;
import com.chl.bookmanager.mapper.ReaderInfoMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmanagerApplicationTests {
    @Autowired
    private BookPredeterMapper bookPredeterMapper;
    @Autowired
    private BookBorrowMapper bookBorrowMapper;
    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Value("${md5.salt}")
    private String salt;
    @Autowired
    ReaderInfoMapper readerInfoMapper;
    @Test
    public void contextLoads() {
        System.out.println(DigestUtils.md5Hex("123"+salt));
    }
    @Test
    public void getDifferent(){
        List<BookPredeter> bookPredeters = bookPredeterMapper.selectAll();
        bookPredeters.forEach(item->{
            System.out.println("时间差"+(int)(new Date().getTime()-(item.getBackDate().getTime()))/(24*60*60*1000));
            if(5<(int)(new Date().getTime()-(item.getBackDate().getTime()))/(24*60*60*1000)){
                System.out.println("删除"+item.getPredeterId());
            }
        });
    }
    @Test
    public void getDifferent2(){
        List<ReaderInfo> readerInfos = readerInfoMapper.selectAll();
        readerInfos.forEach(item->{
            if (item.getPeriodValidity()!=null)
            if(0<(int)(new Date().getTime()-(item.getPeriodValidity().getTime()))/(24*60*60*1000)){
                System.out.println("时间差"+(int)(new Date().getTime()-(item.getPeriodValidity().getTime()))/(24*60*60*1000));
                System.out.println("过期了");
            }
            }
        );
    }
    @Test
    public void Borrow(){
//        Example e = new Example(BookBorrow.class);
//        Example.Criteria c = e.createCriteria();
//        c.andIsNull("returnDate");
//        List<BookBorrow> bookBorrows = bookBorrowMapper.selectByExample(e);//所有未归还图书
//        bookBorrows.forEach(item->{
//            int day=bookBorrowMapper.AllowedBorrowAgain(item.getUid(),item.getBookId());
//            if(day<0){//逾期了
//                BigDecimal fine = new BigDecimal(Math.abs(day)).multiply(new BigDecimal(0.5));//逾期天数*0.5
//                BigDecimal price = bookInfoMapper.selectByPrimaryKey(item.getBookId()).getPrice();//书价
//                if(fine.compareTo(price) > 0) {//罚金超出书价
//                    item.setFine(price);
//                }else {
//                    item.setFine(fine);
//                }
//                bookBorrowMapper.updateByPrimaryKey(item);
//            }
//        });
    }
    }

