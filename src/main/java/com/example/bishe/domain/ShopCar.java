package com.example.bishe.domain;




import javax.persistence.*;


//@Entity(name = "bs_shopcar")
public class ShopCar {
//    @Id
//    @Column(name = "user_id")
    private Integer userId;
//    @Column(name = "article_id")
    private Integer articleId;
//    @Column(name = "buy_num")
    private Integer buyNum;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "ShopCar{" +
                "userId=" + userId +
                ", articleId=" + articleId +
                ", buyNum=" + buyNum +
                '}';
    }
}
