package com.chl.bookmanager.service;

import com.chl.bookmanager.domain.BookPredeter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PredeterService {
    List<BookPredeter> findPredeter(Integer uid);
}
