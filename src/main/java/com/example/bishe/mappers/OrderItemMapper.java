package com.example.bishe.mappers;

import com.example.bishe.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    public List<OrderItem> getOrderItemByOrderCode(String orderCode);
    public int delOrderItem(String orderCode);
    public int insertOrderItem(OrderItem orderItem);
}
