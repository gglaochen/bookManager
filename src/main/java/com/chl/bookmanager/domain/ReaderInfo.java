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
@Table(name = "t_reader")//否则默认数据库表名book_info
public class ReaderInfo {
    @Id
    private Integer uid;            //用户id
    private String username;        //用户名
    private String password;        //密码
    private String readername;      //读者姓名
    private String img;             //头像存放地址
    private Integer sex;            //性别1男2女
    private Integer typeId;          //读者类型
    private String phone;           //电话
    private BigDecimal cashPledge;  //押金
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regDate;           //注册时间
    private Integer isPeriod;       //是否有有效期1有0没有
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date periodValidity;    //有效期至
    private Integer status;          //状态1正常2过期3挂失4注销

}
