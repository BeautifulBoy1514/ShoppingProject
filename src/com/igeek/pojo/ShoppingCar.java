package com.igeek.pojo;

/**
 * 购物车类
 * @author Jia
 */
public class ShoppingCar {
    //用户编号
    private int userId;
    //商品编号
    private int goodsId;
    //商品图片
    private String goodsImage;
    //名称
    private String goodsName;
    //价格
    private double price;
    //数量
    private int saleCount;

    public ShoppingCar(int userId, int goodsId, int saleCount) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.saleCount = saleCount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }
}
