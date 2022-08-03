package com.example.bishe.mappers;

import com.example.bishe.domain.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    public List<Order> getOrderByUserId(Integer userId);
    public  int insertOrder(Order order);
    public int delOrder(String orderCode);
}
