package com.chl.bookmanager.service.impl;

import com.chl.bookmanager.domain.BookPredeter;
import com.chl.bookmanager.mapper.BookPredeterMapper;
import com.chl.bookmanager.service.PredeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author:Administrator
 * @date:2019/1/5/005
 */
@Service
public class PredeterServiceImpl implements PredeterService {
    @Autowired
    private BookPredeterMapper predeterMapper;
    public List<BookPredeter> findPredeter(Integer uid){
        Example example = new Example(BookPredeter.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uid",uid);
        return predeterMapper.selectByExample(example);
    }
}
