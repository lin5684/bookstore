package com.example.bishe.mappers;

import com.example.bishe.domain.ShopCar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopCarMapper {
    public List<ShopCar> getShopCarByUserId(Integer userId);

    public ShopCar getShopCarByUserIdAndArticle(Integer userId,Integer articleId);

    public int insertShopCar(Integer userId, Integer articleId,Integer  buyNum);

    public int delBygoodIdAndUserId(Integer userId,Integer articleId);

    public int deleteGoods(Integer userId);

    public int updateShopCar(ShopCar shopCar);

}
