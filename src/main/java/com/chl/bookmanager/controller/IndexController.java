package com.chl.bookmanager.controller;

import com.chl.bookmanager.domain.ReaderInfo;
import com.chl.bookmanager.domain.UserType;
import com.chl.bookmanager.mapper.UserTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author:Administrator
 * @date:2018/12/25/025
 */
@Controller
public class IndexController {
    @Autowired
    private UserTypeMapper userTypeMapper;
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("top")
    public String top() {
        return "admin/top";
    }
    @RequestMapping("left")
    public String left() {
        return "admin/left";
    }
    @RequestMapping("borrowManager")
    public String borrowManager() {
        return "admin/borrowManager";
    }

    @RequestMapping("index")
    public String index2() {
        return "index";
    }
    @RequestMapping("login")
    public String login(){
        return "login";
    }
    @RequestMapping("admin")
    public String admin(){
        return "admin/admin";
    }
    @RequestMapping("personal")
    public String personal(HttpSession session, Model model){
        ReaderInfo readerInfo = (ReaderInfo) session.getAttribute("user");
        UserType userType = userTypeMapper.selectByPrimaryKey(readerInfo.getTypeId());
        model.addAttribute("userType",userType.getTypeName());
        return "personal";
    }
    @RequestMapping("register")
    public String register(){
        return "register";
    }
    @RequestMapping("pic")
    public String pic(){return "E:/j2ee-chl/pic/12/12/02400a76-43a6-4f9f-b85c-82eeb0d75deb-ab.png";}
}
