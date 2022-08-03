package com.example.bishe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

public class Order {
    private Integer id;
    private String orderCode;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Timestamp createTime;
    private double amount;
    private String status;//订单状态1,
    private Integer userId;
//    private OrderItem[] orderItem;
//
//    public OrderItem[] getOrderItem() {
//        return orderItem;
//    }
//
//    public void setOrderItem(OrderItem[] orderItem) {
//        this.orderItem = orderItem;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCode='" + orderCode + '\'' +
                ", createTime=" + createTime +
                ", amount=" + amount +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
