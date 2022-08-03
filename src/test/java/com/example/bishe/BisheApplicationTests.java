package com.example.bishe;

import com.example.bishe.domain.*;
import com.example.bishe.mappers.*;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BisheApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void getUser() {
        User user = userMapper.selectUser("lxh");
        System.out.print(user.toString());
    }

    @Test
    public void getArticle() {
        List<Article> article = articleMapper.selectAllArticle();
        for (Article ex : article) {
            System.out.println(ex.toString());
        }
    }

    @Test
    public void getsort() {
        //分页策略
        PageHelper.startPage(3, 3);
        List<Article> list = articleMapper.selectByTypeCode("it");
//        PageInfo<Article> info=new PageInfo<>(list);
//        List<Article>    listSort=info.getList();
        for (Article r : list) {
            System.out.println(r.toString());
        }
    }

    //
    @Autowired
    private ShopCarMapper shopCarMapper;

    @Test
    public void getShopCar() {
        List<ShopCar> list = shopCarMapper.getShopCarByUserId(1);
        for (ShopCar r : list) {
            System.out.println(r.toString());
        }
    }

    @Test
    public void insertShopCar() {

//    ShopCar shopCar=new ShopCar();
//    shopCar.setUserId(3);
//    shopCar.setArticleId(5);
//    shopCar.setBuyNum(7);
//    shopCarReposity.save(shopCar);
        shopCarMapper.insertShopCar(1, 2, 3);
    }

    @Test
    public void dele() {
        shopCarMapper.delBygoodIdAndUserId(1, 2);
    }

    @Test
    public void getArticleById() {
        Article article = articleMapper.selectById(2);
        System.out.println(article.toString());
    }

    @Test
    public void delShopCar() {
        shopCarMapper.delBygoodIdAndUserId(3, 2);
        System.out.println("ok");
    }

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void getOrder() {
        List<Order> list = orderMapper.getOrderByUserId(1);
        for (Order o : list) {
            System.out.println(o.toString());
        }
    }

    @Test
    void insertOrder() {
        Order order = new Order();
        Date date = new Date();//Date是java.util.Date
        Timestamp time = new Timestamp(date.getTime());
        order.setCreateTime(time);
        order.setAmount(200);
        order.setStatus("发货");
        order.setOrderCode("qwq");
        order.setUserId(1);
        orderMapper.insertOrder(order);
    }

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Test
    void selectOrderItem(){

        List<OrderItem> list= orderItemMapper.getOrderItemByOrderCode("qwq");
            for (OrderItem l:list){
                System.out.println(l.toString());
            }
    }
    @Test
    void delOrderItem(){

       orderItemMapper.delOrderItem("qwq");
    }
}
