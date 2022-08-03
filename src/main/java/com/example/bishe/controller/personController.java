package com.example.bishe.controller;

import com.example.bishe.domain.User;
import com.example.bishe.mappers.UserMapper;
import com.example.bishe.tol.GetM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class personController {
    @Autowired
    private UserMapper userMapper;

    private User user;

    @RequestMapping("/person")
    public String getUser(Model model, @RequestParam(name = "message", required = false) String message) {

        user = userMapper.selectUser(GetM.getUserName());
        String role;
        try {
            if (message == null) {
               System.out.println("没有改变个人信息");
            } else {
                model.addAttribute("userUpdata", "success");
            }

            if (user.getRole().equals("1")) {
                role = "普通用户";
            } else {
                role = "管理员";
            }

            model.addAttribute("username", user.getUsername());
            model.addAttribute("password", user.getPassword());
            model.addAttribute("name", user.getName());
            model.addAttribute("sex", user.getSex());
            model.addAttribute("phone", user.getPhone());
            model.addAttribute("address", user.getAddress());
            model.addAttribute("role", role);
            model.addAttribute("email", user.getEmail());
            System.out.println("成功获取个人信息");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/person/person";
    }


    //    @ResponseBody
    @RequestMapping("/personSave")
    public String baocun(User user, Model model) {
        //加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
       if (user.getPassword()!=null){
        String pwd=  bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(pwd);
       }
        userMapper.updateUser(user);
        return "redirect:/person?message=change";
    }

}