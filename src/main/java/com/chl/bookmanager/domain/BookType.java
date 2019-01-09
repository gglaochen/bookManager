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
@Table(name = "book_type")//否则默认数据库表名book_info
public class BookType {
    @Id
    private String bookType;    //书类型编号
    private String typeName;    //类型名称
    private String parentType;  //父类类型的编号，1为根分类
}
