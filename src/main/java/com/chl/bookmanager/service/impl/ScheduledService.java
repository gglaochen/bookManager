package com.chl.bookmanager.service.impl;

import com.chl.bookmanager.domain.BookBorrow;
import com.chl.bookmanager.domain.BookInfo;
import com.chl.bookmanager.domain.BookPredeter;
import com.chl.bookmanager.domain.ReaderInfo;
import com.chl.bookmanager.mapper.BookBorrowMapper;
import com.chl.bookmanager.mapper.BookInfoMapper;
import com.chl.bookmanager.mapper.BookPredeterMapper;
import com.chl.bookmanager.mapper.ReaderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author:Administrator
 * @date:2019/1/4/004
 * 定时任务
 */
@Async
@Component
public class ScheduledService {
    @Autowired
    private BookPredeterMapper bookPredeterMapper;
    @Autowired
    private BookBorrowMapper bookBorrowMapper;
    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Autowired
    private ReaderInfoMapper readerInfoMapper;

    @Scheduled(cron = "0 0 0 * * *")
    public void readerOverDue(){//修改过期读者状态
        List<ReaderInfo> readerInfos = readerInfoMapper.selectAll();
        readerInfos.forEach(item->{
                    if (item.getPeriodValidity()!=null)
                        if(0<(int)(new Date().getTime()-(item.getPeriodValidity().getTime()))/(24*60*60*1000)){
                            item.setStatus(1);
                            readerInfoMapper.updateByPrimaryKey(item);
                        }
                }
        );
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void outOfPredeter(){//删除过期预定信息
        List<BookPredeter> bookPredeters = bookPredeterMapper.selectAll();
        bookPredeters.forEach(item->{
            if(item.getOrderNo()==-1) {//已经借阅
                if (5 < (int) (new Date().getTime() - (item.getBackDate().getTime())) / (24 * 60 * 60 * 1000)) {//预定到5天后仍没有借阅
                    bookPredeterMapper.delete(item);
                }
            }
        });
    }
    @Scheduled(cron = "0 0 0 * * *")
    public void changeFine(){//修改罚金
        Example e = new Example(BookBorrow.class);
        Example.Criteria c = e.createCriteria();
        c.andIsNull("returnDate");
        List<BookBorrow> bookBorrows = bookBorrowMapper.selectByExample(e);//所有未归还图书
        bookBorrows.forEach(item->{
            int day=bookBorrowMapper.AllowedBorrowAgain(item.getUid(),item.getBookId());
            if(day<0){//逾期了
                BigDecimal fine = new BigDecimal(Math.abs(day)).multiply(new BigDecimal(0.5));//逾期天数*0.5
                BigDecimal price = bookInfoMapper.selectByPrimaryKey(item.getBookId()).getPrice();//书价
                if(fine.compareTo(price) > 0) {//罚金超出书价
                    item.setFine(price);
                }else {
                    item.setFine(fine);
                }
                bookBorrowMapper.updateByPrimaryKey(item);
            }
        });
    }
}
