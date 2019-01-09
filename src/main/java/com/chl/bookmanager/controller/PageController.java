package com.chl.bookmanager.controller;

import com.chl.bookmanager.exception.BizException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author:Administrator
 * @date:2018/12/24/024
 */
@Controller
public class PageController {

    @ExceptionHandler(BizException.class)
    public String exPage(Exception ex, Model model){//��controller��ֻ��ҵ���쳣�Ĵ���
        model.addAttribute("ex",ex);
        return "/error/biz";
    }

    @GetMapping("/e500")
    public String e500(){
        throw new RuntimeException("runtimeException");
    }

    @GetMapping("/add")
    public String add(){
        throw new BizException("添加错误");
    }
}
