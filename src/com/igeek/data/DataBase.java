package com.igeek.data;

import com.igeek.pojo.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {
    //用户数据
    private  ArrayList<User> userData = new ArrayList<>();
    //商品数据
    private ArrayList<Goods> goodsData = new ArrayList<>();
    //商品类别
    private  ArrayList<GoodsClass> goodsClassData = new ArrayList<>();
    //订单数据
    private HashMap<Integer,ArrayList<Order>> orderData = new HashMap<>();
    //购物车数据
    private HashMap<Integer,ArrayList<ShoppingCar>> carData = new HashMap<>();

    public ArrayList<User> getUserData() {
        return userData;
    }

    public void setUserData(ArrayList<User> userData) {
        this.userData = userData;
    }

    public ArrayList<Goods> getGoodsData() {
        return goodsData;
    }

    public void setGoodsData(ArrayList<Goods> goodsData) {
        this.goodsData = goodsData;
    }

    public ArrayList<GoodsClass> getGoodsClassData() {
        return goodsClassData;
    }

    public void setGoodsClassData(ArrayList<GoodsClass> goodsClassData) {
        this.goodsClassData = goodsClassData;
    }

    public HashMap<Integer, ArrayList<Order>> getOrderData() {
        return orderData;
    }

    public void setOrderData(HashMap<Integer, ArrayList<Order>> orderData) {
        this.orderData = orderData;
    }

    public HashMap<Integer, ArrayList<ShoppingCar>> getCarData() {
        return carData;
    }
    public void setCarData(HashMap<Integer, ArrayList<ShoppingCar>> carData) {
        this.carData = carData;
    }

    /**
     *
     */

    public DataBase(){
        //初始化用户数据
        userData.add(new User(1,"admin","000"));
        userData.add(new User(2,"Tom","123"));
        //初始化商品类别数据
        goodsClassData.add(new GoodsClass(1, "家用电器", "jydq"));
        goodsClassData.add(new GoodsClass(2, "电脑  ", "dn"));
        goodsClassData.add(new GoodsClass(3, "手机  ", "sj"));
        goodsClassData.add(new GoodsClass(4, "服装  ", "fz"));

        //初始化商品数据
        goodsData.add(new Goods(101, "(SONY)KD-85Z9G   ", 119999, 1, 100, "5abca3f7N357415cf.jpg", "小米", 100));
        goodsData.add(new Goods(102, "西门子对开门冰箱  ", 26699, 1, 200, "ef34f15d34e0ddee.jpg", "美的", 50));
        goodsData.add(new Goods(103, "华硕2080ti ROG   ", 12999, 2, 300, "f111585c1abd104a.jpg", "华硕", 60));
        goodsData.add(new Goods(104, "芝奇32G3200HZ内存", 1999, 2, 400, "5a7e5f487b59e98d.jpg", "芝奇", 20));
        goodsData.add(new Goods(105, "AMD YES !  YES!  ", 2599, 2, 500, "5bbf43e7Ndd80aff7.jpg", "英特尔", 30));
        goodsData.add(new Goods(106, "(ASUS)TUF B360M +", 689, 2, 600, "5a1fb25cNee12052f.jpg", "海韵", 120));
        goodsData.add(new Goods(107, "ROG Phone        ", 5999, 3, 700, "a2c208410ae84d1f.jpg", "苹果", 80));
        goodsData.add(new Goods(108, "华为P30          ", 4288, 3, 800, "7e45d0bb0b33f566.jpg", "华为", 150));
        goodsData.add(new Goods(109, "三星GlaxyS10     ", 5969, 3, 900, "a1bda439038caa12.jpg", "三星", 70));
        goodsData.add(new Goods(110," 匡威官方 经典款",439,4,10,"dc86c650f13645e1.jpg","匡威",300));
    }
}
