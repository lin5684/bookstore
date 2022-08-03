package com.example.bishe.domain;

import org.apache.ibatis.javassist.runtime.Inner;

public class Article {
    private Integer id;
    private String title;
    private String supplier;//供应商
    private double price;
    private double discount;
    private String locality;//发货地址
    private Integer storage;
    private String img;
    private String description;
    private String typeCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", supplier='" + supplier + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", locality='" + locality + '\'' +
                ", storage=" + storage +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", type_code='" + typeCode + '\'' +
                '}';
    }
}
