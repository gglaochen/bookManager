package com.chl.bookmanager.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
public interface BookPredeterService {
    boolean predetorTooMuch(Integer bookId);

    Integer getMaxOrderNo(Integer bookId);

    Date findRecentReturn(Integer bookId,Integer orderNo);

    void addPredeter(Integer bookId, Integer userId, Integer orderNo);
}
