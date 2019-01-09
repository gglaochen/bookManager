package com.chl.bookmanager.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:Administrator
 * @date:2018/12/25/025
 */
@Data//只含无参构造函数
@AllArgsConstructor//使用全参注解后会让Data的无参构造函数取消
@NoArgsConstructor
@Table(name = "t_borrow")//否则默认数据库表名book_info
public class BookBorrow {
    @Id
    private Integer borrowId;       //借阅编号
    private Integer uid;            //读者id
    private Integer bookId;         //图书id
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date borrowDate;        //借阅时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;        //归还时间
    private Integer isLost;         //是否丢失，1为丢失，0为未丢失
    private BigDecimal fine;        //罚金
    private Integer isPaid;         //罚金是否已交
    private Integer isRenew;        //是否已续借
}
