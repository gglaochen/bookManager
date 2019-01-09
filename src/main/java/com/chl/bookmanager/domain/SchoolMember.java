package com.chl.bookmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author:Administrator
 * @date:2018/12/27/027
 */
@Data//只含无参构造函数
@AllArgsConstructor//使用全参注解后会让Data的无参构造函数取消
@NoArgsConstructor
@Table(name = "t_member")//否则默认数据库表名book_info
public class SchoolMember {
    @Id
    private String memberId;      //学号，教职工号
    private Integer typeId; //类型名
    private Integer isUsed; //是否已使用
    private Integer uid;     //绑定的阅读卡编号
}
