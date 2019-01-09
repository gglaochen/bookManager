package com.chl.bookmanager.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:Administrator
 * @date:2018/12/20/020
 */
@Data//只含无参构造函数
@AllArgsConstructor//使用全参注解后会让Data的无参构造函数取消
@NoArgsConstructor
@Table(name = "t_book")//否则默认数据库表名book_info
public class BookInfo {//ctrl+f12查看该类的所有方法
    @Id//设置该标签的属性为主键，然后可以使用通用Mapper的ByPrimaryKey
    private Integer bookId;            //图书id
    private Integer bookCountry;       //图书所属国家1中国2英国3美国4北欧5其他地区
    private String bookType;           //图书所属类型编号
    private Integer bookLoc;           //图书所在书架位置
    @Column(name = "ISBN")
    private String ISBN;               //ISBN唯一标识
    private String bookName;           //图书名称
    private String author;             //作者
    private Integer isOffprint;        //是否是单行本1是0不是
    private Integer orderNum;          //这是系列书第几册，单行本为1
    private Integer seriesNum;         //系列总共有几册
    private String publish;            //出版社名称
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;          //出版日期
    private BigDecimal price;          //价格
    private Integer maxNum;            //最大库存数量
    private Integer borrowNum;         //借出数量
    private Integer restNum;           //剩余数量
    private String info;               //简介
    private String bookImage;          //图书图片
}
