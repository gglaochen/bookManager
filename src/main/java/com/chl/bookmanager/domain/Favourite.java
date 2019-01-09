package com.chl.bookmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author:Administrator
 * @date:2019/1/1/001
 */
@Data//只含无参构造函数
@AllArgsConstructor//使用全参注解后会让Data的无参构造函数取消
@NoArgsConstructor
@Table(name = "t_favourite")//否则默认数据库表名book_info
public class Favourite {
    @Id
    private Integer favouriteId;
    private Integer uid;
    private Integer bookId;
}
