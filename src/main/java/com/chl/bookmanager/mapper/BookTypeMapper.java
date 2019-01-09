package com.chl.bookmanager.mapper;

import com.chl.bookmanager.common.BaseMapper;
import com.chl.bookmanager.domain.BookInfo;
import com.chl.bookmanager.domain.BookType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface BookTypeMapper extends BaseMapper<BookType> {
}
