package com.chl.bookmanager.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author:Administrator
 * @date:2019/1/2/002
 */
@Data//只含无参构造函数
@AllArgsConstructor//使用全参注解后会让Data的无参构造函数取消
@NoArgsConstructor
@Table(name = "t_predeter")//否则默认数据库表名book_info
public class BookPredeter {
    @Id
    private Integer predeterId;
    private Integer uid;
    private Integer bookId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date backDate;
    private Integer orderNo;
}
