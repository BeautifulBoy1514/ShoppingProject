package com.igeek.pojo;
/**
 * @author Yongqiang Jia
 * @version 1.0
 */

public class Goods {
    //商品编号
    private int goodsId;
    //商品名称
    private  String goodsName;
    //价格
    private double price;
    //类型编号
    private  int classId;
    //库存
    private int stoage;
    //图片路径
    private String goodsImage;
    //品牌
    private String brand;
    //商品销量
    private int saleCount;

    public Goods(int goodsId, String goodsName, double price, int classId, int stoage, String goodsImage, String brand, int saleCount) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.price = price;
        this.classId = classId;
        this.stoage = stoage;
        this.goodsImage = goodsImage;
        this.brand = brand;
        this.saleCount = saleCount;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setStoage(int stoage) {
        this.stoage = stoage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSaleCount(int sellCount) {
        this.saleCount = sellCount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public double getPrice() {
        return price;
    }

    public int getClassId() {
        return classId;
    }

    public int getStoage() {
        return stoage;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public String getBrand() {
        return brand;
    }

    public int getSaleCount() {
        return saleCount;
    }
}
