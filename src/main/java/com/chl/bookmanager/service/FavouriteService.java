package com.chl.bookmanager.service;

import com.chl.bookmanager.domain.BookInfo;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.server.InactiveGroupException;

import java.util.List;

@Transactional
public interface FavouriteService {
    int addFavourite(Integer userId,Integer bookId);

    List<BookInfo> findFavourite(Integer uid);

    boolean findFavouriteExist(Integer uid, Integer bookId);

    int deleteFavourite(Integer userId, Integer bookId);
}
