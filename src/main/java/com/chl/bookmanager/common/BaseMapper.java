package com.chl.bookmanager.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/*
        项目中所有mapper类父类
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
