package com.chl.bookmanager.mapper;

import com.chl.bookmanager.common.BaseMapper;
import com.chl.bookmanager.domain.BookInfo;
import com.chl.bookmanager.domain.Favourite;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteMapper extends BaseMapper<Favourite> {
    List<BookInfo> findFavourite(Integer uid);
}
