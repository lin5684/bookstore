package com.example.bishe.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.Session;
import java.util.Calendar;

@Controller
public class FileController {
@GetMapping("/userLogin")
    public String toLogin(Model model){
    model.addAttribute("CurrentYear", Calendar.getInstance().get(Calendar.YEAR));
    return "/login/login";
}


}
