package com.chl.bookmanager.mapper;

import com.chl.bookmanager.common.BaseMapper;
import com.chl.bookmanager.domain.BookInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoMapper extends BaseMapper<BookInfo> {

    void updateBorrowOut(Integer bookId);

}
