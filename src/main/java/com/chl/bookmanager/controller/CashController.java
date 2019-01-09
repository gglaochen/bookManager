package com.chl.bookmanager.controller;

import com.chl.bookmanager.domain.ReaderInfo;
import com.chl.bookmanager.domain.ResponseResult;
import com.chl.bookmanager.mapper.ReaderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @author:Administrator
 * @date:2019/1/6/006
 */
@Controller
@RequestMapping("cash")
public class CashController {
    @Autowired
    private ReaderInfoMapper readerInfoMapper;

    @GetMapping("support")
    public String support() {
        return "admin/support";
    }

    @GetMapping("returnCash")
    public String returnCash() {
        return "admin/returnCash";
    }

    @ResponseBody
    @PostMapping("toSupport")
    public ResponseResult<Void> toSupport(Integer uid, BigDecimal cash) {
        if (uid == null || cash == null || cash.compareTo(new BigDecimal(0)) <= 0) {
            return new ResponseResult<Void>(0, "用户id，押金不能为空或小于0");
        }
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(uid);
        if (readerInfo == null) {
            return new ResponseResult<Void>(0, "该用户名不存在");
        }
        readerInfo.setCashPledge(readerInfo.getCashPledge().add(cash));
        readerInfoMapper.updateByPrimaryKey(readerInfo);
        return new ResponseResult<Void>(1, "提交押金成功");
    }

    @ResponseBody
    @PostMapping("checkCash")
    public ResponseResult<Void> checkCash(Integer uid) {
        System.out.println("到了cash");
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(uid);
        if (readerInfo == null) {
            return new ResponseResult<Void>(0, "该用户名不存在");
        }
        if (readerInfo.getCashPledge().compareTo(new BigDecimal(0)) <= 0) {
            return new ResponseResult<Void>(0, "您没有可退押金");
        } else {
            return new ResponseResult<Void>(1, "请确认退还" + readerInfo.getCashPledge() + "元现金");
        }
    }

    @ResponseBody
    @PostMapping("toReturnCash")
    public ResponseResult<Void> toReturnCash(Integer uid) {
        ReaderInfo readerInfo = readerInfoMapper.selectByPrimaryKey(uid);
        readerInfo.setCashPledge(new BigDecimal(0));
        try {
            readerInfoMapper.updateByPrimaryKey(readerInfo);
        } catch (Exception e) {
            return new ResponseResult<Void>(0, "退还失败");
        }
        return new ResponseResult<Void>(1, "退还成功");
    }
}
