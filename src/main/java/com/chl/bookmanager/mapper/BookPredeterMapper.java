package com.chl.bookmanager.mapper;

import com.chl.bookmanager.common.BaseMapper;
import com.chl.bookmanager.domain.BookPredeter;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookPredeterMapper extends BaseMapper<BookPredeter> {
    Integer getMaxOrderNo(Integer bookId);

    Date findRecentReturn(Integer bookId,Integer orderNo);

    List<BookPredeter> selectByOrder(Integer bookId, int num);
}
