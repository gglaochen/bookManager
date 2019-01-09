package com.chl.bookmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author:Administrator
 * @date:2018/12/25/025
 */
@Data//只含无参构造函数
@AllArgsConstructor//使用全参注解后会让Data的无参构造函数取消
@NoArgsConstructor
@Table(name = "user_type")//否则默认数据库表名book_info
public class UserType {
    @Id
    private Integer typeId;         //读者类型编号
    private String typeName;        //类型名称
    private Integer maxBorrowNum;   //最多借书数量
    private Integer limitDay;       //最长借书天数
}
