package com.chl.bookmanager.service.impl;

import com.chl.bookmanager.domain.BookInfo;
import com.chl.bookmanager.domain.Favourite;
import com.chl.bookmanager.mapper.FavouriteMapper;
import com.chl.bookmanager.service.FavouriteService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author:Administrator
 * @date:2019/1/1/001
 */
@Service
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    private FavouriteMapper favouriteMapper;
    @Override
    public int addFavourite(Integer userId, Integer bookId) {
        Favourite favourite = new Favourite();
        favourite.setUid(userId);
        favourite.setBookId(bookId);
        return favouriteMapper.insert(favourite);
    }

    @Override
    public List<BookInfo> findFavourite(Integer uid) {
        return favouriteMapper.findFavourite(uid);
    }

    @Override
    public boolean findFavouriteExist(Integer uid, Integer bookId) {
        Example e =new Example(Favourite.class);
        Example.Criteria criteria = e.createCriteria();
        criteria.andEqualTo("uid",uid).andEqualTo("bookId",bookId);
        return favouriteMapper.selectOneByExample(e)!=null;
    }

    @Override
    public int deleteFavourite(Integer userId, Integer bookId) {
        Example e =new Example(Favourite.class);
        Example.Criteria criteria = e.createCriteria();
        criteria.andEqualTo("uid",userId).andEqualTo("bookId",bookId);
        return  favouriteMapper.deleteByExample(e);
    }
}
