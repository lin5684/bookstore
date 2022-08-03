package com.example.bishe.domain;

public class OrderItem {
    private String orderCode;
    private Integer articleId;
    private Integer buyNum;
    private String address;

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderCode='" + orderCode + '\'' +
                ", articleId=" + articleId +
                ", buyNum=" + buyNum +
                ", img='" + address + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address =address;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

}
