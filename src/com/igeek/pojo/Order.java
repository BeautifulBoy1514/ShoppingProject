package com.igeek.pojo;

import java.util.Date;

public class Order {
    //订单编号 商品类型简称+商品编号+交易人编号+服务器本地时间毫秒值；
    private String orderId;
    //用户编号
    private int userId;
    //商品编号
    private int goodsId;
    //销售数量
    private int saleCount;
    //销售时间
    private Date saleTime;

    public Order(String orderId, int userId, int goodsId, int saleCount, Date saleTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.goodsId = goodsId;
        this.saleCount = saleCount;
        this.saleTime = saleTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }
}
