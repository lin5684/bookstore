package com.example.bishe.controller;

import com.example.bishe.domain.Article;
import com.example.bishe.domain.ShopCar;
import com.example.bishe.domain.User;
import com.example.bishe.mappers.ArticleMapper;
import com.example.bishe.mappers.ShopCarMapper;
import com.example.bishe.mappers.UserMapper;
import com.example.bishe.tol.GetM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class shopcarController {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ShopCarMapper shopCarMapper;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/shopcar")
    public String shopcar(Model model) {

        User user = userMapper.selectUser(GetM.getUserName());
        System.out.println(user.getId());
        List<ShopCar> carList = shopCarMapper.getShopCarByUserId(user.getId());
        List<Article> articles = new ArrayList<>();
        try {
            if (carList != null) {
                for (ShopCar s : carList) {
                    articles.add(articleMapper.selectById(s.getArticleId()));
                }
            }
            if (articles != null) {
                System.out.println("购物车有商品");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("goodsMessage", articles);
        model.addAttribute("usershopcar", carList);
        return "/shopcar/shopcar";
    }


    @RequestMapping("/inShopcar")
    public String inShopCar(Model model, @RequestParam(name = "goods", required = false) Integer article_Id) {

        User user = userMapper.selectUser(GetM.getUserName());
        ShopCar shopCar = shopCarMapper.getShopCarByUserIdAndArticle(user.getId(), article_Id);
        //存存判断
        Article article = articleMapper.selectById(article_Id);
        if (article.getStorage() >= 1) {
            if (shopCar == null) {
                shopCarMapper.insertShopCar(user.getId(), article_Id, 1);
                return "redirect:/shopcar";
            } else {
                if (article.getStorage() >= shopCar.getBuyNum() + 1) {
                    shopCar.setBuyNum(shopCar.getBuyNum() + 1);
                    shopCarMapper.updateShopCar(shopCar);
                    return "redirect:/shopcar";
                } else {
                    System.out.println("库存不足");
                    return "redirect:/introduce?kucun=buzu&&introduce=" + article_Id;
                }
            }
        } else {
            System.out.println("库存不足");
            return "redirect:/introduce?kucun=buzu&&introduce=" + article_Id;
        }

    }


    @RequestMapping(value = "/doCar", method = {RequestMethod.GET})
    @ResponseBody
    public String doCar(Model model, @RequestParam(value = "spc", required = false) Integer num,
                        @RequestParam(value = "artId", required = false) Integer articleId) {

        User user = userMapper.selectUser(GetM.getUserName());
        Article article = articleMapper.selectById(articleId);
        ShopCar shopCar = new ShopCar();
        shopCar.setUserId(user.getId());
        if (article.getStorage() >= num) {
            shopCar.setBuyNum(num);
            shopCar.setArticleId(articleId);
            shopCarMapper.updateShopCar(shopCar);
            System.out.println("购买数量修改成功");
            return "修改成功";
        } else {
            String s = null;//原先数值
//            model.addAttribute("kucun", "库存不足");
                 System.out.println("库存不足");
                shopCar = shopCarMapper.getShopCarByUserIdAndArticle(user.getId(), articleId);
                Integer yuanxian = new Integer(shopCar.getBuyNum());
                s = yuanxian.toString();

            return s;

        }

    }

    @RequestMapping("/delCar")
    public String delCar(@RequestParam(value = "artId", required = false) Integer articleId
            , @RequestParam(value = "delAll", required = false) String isdelAll) {

        User user = userMapper.selectUser(GetM.getUserName());

        if (articleId != null) {
            shopCarMapper.delBygoodIdAndUserId(user.getId(), articleId);
            System.out.println("删除成功");
        } else {
            if (isdelAll != null && isdelAll.equals("delshopcar")) {
                shopCarMapper.deleteGoods(user.getId());
                System.out.println("清空成功");
            } else {
                System.out.println("清空失败");
            }
        }
        return "redirect:/shopcar";
    }
}
