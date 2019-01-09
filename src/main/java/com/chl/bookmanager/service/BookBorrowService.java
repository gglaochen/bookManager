package com.chl.bookmanager.service;

import com.chl.bookmanager.domain.BookInfo;
import com.chl.bookmanager.domain.BookRankVO;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Transactional
public interface BookBorrowService {
    List<BookRankVO> getRank(Date startDate, Date finalDate);

    boolean hasBook(Integer userId, Integer bookId);

    boolean notReturn(Integer userId);

    int borrow(Integer userId, Integer bookId);

    void borrowAgain(Integer userId, Integer bookId);

    BigDecimal checkFine(Integer userId, Integer bookId);

    void returnBook(Integer userId, Integer bookId, Integer isLost);
}
