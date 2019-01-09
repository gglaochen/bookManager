package com.chl.bookmanager.mapper;

import com.chl.bookmanager.common.BaseMapper;
import com.chl.bookmanager.domain.BookBorrow;
import com.chl.bookmanager.domain.BookBorrowVO;
import com.chl.bookmanager.domain.BookInfo;
import com.chl.bookmanager.domain.BookRankVO;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface BookBorrowMapper extends BaseMapper<BookBorrow> {
    List<BookInfo> getRank(Date startDate, Date finalDate);

    List<BookBorrowVO> selectBorrowVO(Integer uid);

    Integer AllowedBorrowAgain(Integer userId, Integer bookId);

    Integer selectBorrowCount(Integer bookId, Date startDate, Date finishDate);
}
