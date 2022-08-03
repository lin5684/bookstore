package com.example.bishe.controller;

import com.example.bishe.domain.User;
import com.example.bishe.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class loginController {
    @Autowired
    private UserMapper userMapper;
    private User user;

    @RequestMapping("/zhuce")
    public String zhuce(User user, Model model) {
        //加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(pwd);
        userMapper.insertUser(user);
        System.out.println("注册成功");
        return "redirect:/userLogin";
    }


}
