//package com.example.bishe.repository;
//
//import com.example.bishe.domain.ShopCar;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.*;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//public interface ShopCarReposity extends JpaRepository<ShopCar,Integer> {
//    @Query(value = "SELECT * from bs_shopcar c WHERE c.userId= ?1",nativeQuery = true)
//    public List<ShopCar> getShopCarByUserId(Integer userId);
//
//    @Transactional
//    @Modifying
//    @Query(nativeQuery = true, value ="INSERT INTO bs_shopcar  VALUES (?1,?2,?3)")//jpaql不支持insert into
//    public int in(Integer userId, Integer articleId,Integer  buyNum);
//
//    @Transactional
//    @Modifying
//    @Query("DELETE from bs_shopcar c where c.articleId=?2 and c.userId=?1 ")
//    public int delBygoodIdAndUserId(Integer userId,Integer articleId);
//
//    @Transactional
//    @Modifying
//    @Query(value = "DELETE from bs_shopcar c where c.userId=?1")
//    public int deleteGoods(Integer userId);
//}
