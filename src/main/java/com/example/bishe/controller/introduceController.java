package com.example.bishe.controller;

import com.example.bishe.domain.Article;
import com.example.bishe.mappers.ArticleMapper;
import com.example.bishe.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class introduceController {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/introduce")
    public String getIntroduce(Model model, @RequestParam(name = "introduce", required = false) Integer goods_id
            , @RequestParam(name = "kucun", required = false) String kun) {
        if (kun != null) {
            if (kun.equals("buzu")) {
                model.addAttribute("kucun", "库存不足");
            }
        } else {
            System.out.println("为空");

        }
        Article article = articleMapper.selectById(goods_id);
        model.addAttribute("goods_detail", article);
        return "/introduce/introduce";
    }
}
