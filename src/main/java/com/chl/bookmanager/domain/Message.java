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
 * @date:2019/1/1/001
 */
@Data//只含无参构造函数
@AllArgsConstructor//使用全参注解后会让Data的无参构造函数取消
@NoArgsConstructor
@Table(name = "t_message")//否则默认数据库表名book_info
public class Message {
    @Id
    private Integer messageId;          //消息编号
    private Integer uid;                //用户id
    private String message;             //消息内容
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date messageDate;           //消息日期
    private Integer isRead;             //是否已读
}
