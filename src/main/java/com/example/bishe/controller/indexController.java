package com.example.bishe.controller;

import com.example.bishe.domain.Article;
import com.example.bishe.mappers.ArticleMapper;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class indexController {
    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping(path = {"/index", "/success", "/"})
    public String index(Model model, @RequestParam(name = "num", required = false) Integer num,
                        @RequestParam(name = "imgsort", required = false) String sort) {
        if (num == null) {
            num = 1;
        }
        if (sort == null) {
            sort = "it";
        }
        List<Article> sortall = articleMapper.selectByTypeCode(sort);//图片类型
        PageHelper.startPage(num, 6); //分页策略
        List<Article> list = articleMapper.selectByTypeCode(sort);
        int imgLeng = sortall.size();
        if (imgLeng % 6 == 0) {
            imgLeng = imgLeng / 6;
        } else {
            imgLeng = imgLeng / 6 + 1;
        }
        int[] img = new int[imgLeng];
        for (int i = 0; i < imgLeng; i++) {
            img[i] = i + 1;
        }
//        PageInfo<Article> info=new PageInfo<>(list);
//        List<Article>   listSort=info.getList();
        model.addAttribute("imgs", list);
        model.addAttribute("imgLeng", img);
        model.addAttribute("imgsort", sort);


        return "/index";
    }


}
