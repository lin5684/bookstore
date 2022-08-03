package com.example.bishe.controller;

import com.example.bishe.domain.*;
import com.example.bishe.mappers.*;
import com.example.bishe.tol.GetM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class orderController {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ShopCarMapper shopCarMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping("/order")
    public String order(Model model) {

        User user = userMapper.selectUser(GetM.getUserName());
        List<Order> list = orderMapper.getOrderByUserId(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        for (Order l : list) {
            orderItems.addAll(orderItemMapper.getOrderItemByOrderCode(l.getOrderCode()));
        }
        model.addAttribute("orderList", list);
        model.addAttribute("address", user.getAddress());
        return "/order/order";
    }

    @RequestMapping("/inOrder")
    public String getOrder() {
        Date date = new Date();//Date是java.util.Date

        User user = userMapper.selectUser(GetM.getUserName());
        Timestamp time = new Timestamp(date.getTime());
        List<ShopCar> list = shopCarMapper.getShopCarByUserId(user.getId());
        Article article;
        double tol = 0;
        for (ShopCar l : list) {
            article = articleMapper.selectById(l.getArticleId());
            tol = article.getPrice() * l.getBuyNum() + tol;

            OrderItem orderItem = new OrderItem();
            orderItem.setArticleId(l.getArticleId());
            orderItem.setBuyNum(l.getBuyNum());
            orderItem.setOrderCode(GetM.getUserName() + "_" + time);
            orderItem.setAddress(user.getAddress());
            //商品库存修改
            article.setStorage(article.getStorage() - l.getBuyNum());
            articleMapper.updataArticle(article);
            //订单详情增加
            orderItemMapper.insertOrderItem(orderItem);
        }

        //订单增加
        Order order = new Order();
        order.setUserId(user.getId());
        order.setCreateTime(time);
        order.setStatus("待发货");
        order.setOrderCode(GetM.getUserName() + "_" + time);
        order.setAmount(tol);
        //清空购物车
        shopCarMapper.deleteGoods(user.getId());
        orderMapper.insertOrder(order);
        return "redirect:/order";
    }

    @RequestMapping("/delOrder")
    public String delOrder(@RequestParam(name = "orderCode", required = false)
                                   String code) {
        if (code != null) {
            orderItemMapper.delOrderItem(code);
            orderMapper.delOrder(code);
        } else {
            System.out.println("存在空值");
        }
        return "redirect:/order";
    }
}
