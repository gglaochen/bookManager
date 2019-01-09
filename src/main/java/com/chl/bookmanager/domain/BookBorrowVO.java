package com.chl.bookmanager.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:Administrator
 * @date:2019/1/2/002
 */
@Data//只含无参构造函数
@AllArgsConstructor//使用全参注解后会让Data的无参构造函数取消
@NoArgsConstructor
public class BookBorrowVO {
    private String bookName;
    private Integer bookId;
    private Integer uid;
    @Column(name = "ISBN")
    private String ISBN;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date borrowDate;        //借阅时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;        //归还时间
    private Integer isRenew;        //是否已续借
    private BigDecimal fine;        //罚金
    private Integer isPaid;         //罚金是否已交
    private Integer isLost;         //是否丢失
        private Integer timeDifference; //相差时间
}
