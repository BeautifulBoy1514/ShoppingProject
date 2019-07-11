package com.igeek.service;

import com.igeek.data.DataBase;
import com.igeek.pojo.*;
import com.igeek.util.DateUtil;
import com.igeek.util.GenericUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 购物系统，封装了形同应用中的所有业务有逻辑
 */
public class ShoppingSystem {
    //键盘扫描器
    private Scanner sc = new Scanner(System.in);
    //数据库
    private DataBase data = new DataBase();
    //登陆人
    private User loginUser = null;
    //自定义日期工具类
    private DateUtil dateUtil = new DateUtil();
    /**
     * 启动系统
     */
    public void start() {
        System.out.println("1. 登录");
        System.out.println("2. 注册");
        System.out.println("请选择");
        int choice = sc.nextInt();
        //判断选择
        if (choice == 1) {
            //登录
            login();
        } else if (choice == 2) {
            //注册
            regist();
        }
    }

    /**
     * 登录
     */

    public void login() {
        System.out.println("\n---------登录-----------");
        System.out.println("请输入用户名");
        String userName = sc.next();
        System.out.println("请输入密码:");
        String password = sc.next();
        //当前登录的用户
        User user = validateUser(userName);
        //如果找到了用户
        if (user != null) {
            //将输入的密码和用户对象中的密码进行比较
            //如果比较成功
            if (user.getPassword().equals(password)) {
                //登陆成功
                System.out.println("登陆成功");
                //记录当前登录人
                loginUser = user;
                //显示主菜单
                mainMenu();
            }
            //如果比较失败，则提示错误信息
            else {
                System.out.println("密码错误");
                login();
            }
        }
        //如果用户名不存在，则提示错误信息
        else {
            System.out.println("用户名不存在！");
            //回调方法，本方法中回调方法本身，又称递归
            login();
        }

    }

    /**
     * 注册
     */
    public void regist() {
        System.out.println("------注册-------");
        System.out.println("请输入用户名");
        String userName = sc.next();
        System.out.println("请输入密码:");
        String password = sc.next();
        User user = validateUser(userName);
        //如果不存在。可以注册
        if (user == null) {
            //将输入的用户名和密码封装为user对象
            User registUser = new User(++GenericUtil.userId, userName, password);//注意这里的用户id自增方式;
            data.getUserData().add(registUser);
            System.out.println("----注册成功----");
            start();
        }
        //如果存在，用户名已经被注册
        else {
            System.out.println("用户已经被注册");
            //返回起始页面
            start();
        }
    }

    /**
     * 验证用户名是否存在
     * @param userName
     * @return User
     */
    public User validateUser(String userName) {
        User user = null;
        //循环遍历用户数据库
        for (User u : data.getUserData()) {
            //判断数据库中是否存在注册的用户名
            if (u.getUserName().equals(userName)) {
                //记录下当前用户
                user = u;
                break;
            }
        }
        return user;
    }

    /**
     * 主菜单
     */
    public void mainMenu() {
        System.out.println("\n----------主菜单-----------");
        System.out.println("1. 商品类别");
        System.out.println("2. 我的购物车");
        System.out.println("3. 我的订单");
        System.out.println("4. 退出系统");
        System.out.println("请选择:");
        int choice = sc.nextInt();
        //判断选择项
        switch (choice){
            case 1:
                showGoodsClass();
                break;
            case 2:
                myShoppingCar();
                break;
            case 3:
                myOrders();
                break;
            case 4:
                //返回起始页
                start();
                break;
        }
    }
    /**
     * 查看商品类别
     */
    public void showGoodsClass(){
        System.out.println("\n----------商品类别----------");
        //创建Set集合用来筛选不重复的商品类别
        HashSet<Integer> classSet = new HashSet<>();
        //遍历商品类别
        for (Goods goods : data.getGoodsData()){
            //将商品类别编号写入集合
            classSet.add(goods.getClassId());
        }
        ArrayList<GoodsClass> classList = new ArrayList<>();
        //遍历HashSet中的类别编号
        for (Integer classId : classSet){
            //根据类别编号从数据库中查找相应的类别对象
            GoodsClass goodsClass = getGoodsClass(classId);
            //将类别对象添加至classList
            classList.add(goodsClass);
        }
        //对classList遍历，呈现类别名称
        for (int i = 0;i < classList.size();i++){
            //获取每个类别对象
            GoodsClass goodsClass = classList.get(i);
            System.out.println(i+1+". "+goodsClass.getClassName());
        }
        System.out.println("请选择：（输入0返回上一层）");
        //输入序号
        int choice = sc.nextInt();
        //判断序号，是否要返回上一层菜单
        if (choice == 0){
            //进入主菜单
            mainMenu();
        }
        else {
            //计算索引
            int index = choice - 1;
            //根据索引获取选择的类别编号
            GoodsClass goodsClass = classList.get(index);
            //根据商品类别查看商品列表
            showGoodsByClass(goodsClass);
        }
    }

    /**
     * 根据类别编号获取名称
     * @param classId
     * @return
     */
    public String getClassName(int classId){
        String className = null;
        //遍历数据库中的集合类别
        for (GoodsClass goodsClass : data.getGoodsClassData()){
            //判断类别编号是否相同
            if (classId == goodsClass.getClassId()){
                className = goodsClass.getClassName();
                break;
            }
        }
        return className;

    }
    /**
     * 根据类别编号查询类别对象
     * @param classId
     * @return
     */
    public GoodsClass getGoodsClass(int classId){
        GoodsClass classObj = null;
        //遍历数据库中的类别集合
        for (GoodsClass goodsClass : data.getGoodsClassData()){
            //判断类别编号是否相同
            if (classId == goodsClass.getClassId()){
                classObj = goodsClass;
                break;
            }
        }
        return classObj;
    }
    /**
     * 根据商品类被查看商品信息
     */
    public void showGoodsByClass(GoodsClass goodsClass){
        System.out.println("----------"+goodsClass.getClassName()+"-----------");
        //创建goodsList集合，用来存储满足类型的商品
        ArrayList<Goods> goodsList = new ArrayList<>();
        //遍历数据库中的商品集合
        for (Goods goods : data.getGoodsData()) {
            //判断每个商品的类别编号是否和参数的类别编号相同
            if (goods.getClassId() == goodsClass.getClassId()) {
                //如果相同，将商品添加至goodsList集合
                goodsList.add(goods);
            }
        }
        //遍历商品goodsList集合
        for (int i = 0;i < goodsList.size();i++){
            Goods goods = goodsList.get(i);
            //打印呈现序号以及商品名称
            System.out.println(i+1+". "+goods.getGoodsName());
        }
        System.out.println("请选择：（输入0返回上一层）");
        int choice = sc.nextInt();
        //判断选项
        if( choice == 0){
            //返回商品类别
            showGoodsClass();
        }
        else {
            //计算索引
            int index = choice - 1;
            //
            Goods goods = goodsList.get(index);
            //查看商品的详细信息
            showGoodsDetails(goods);
        }
    }

    /**
     * 查看商品详细信息
     */
    public void showGoodsDetails(Goods goods){
        System.out.println("\n-----------商品详情---------");
        System.out.println(goods.getGoodsName());
        System.out.println("品牌："+goods.getBrand());
        System.out.println("价格：￥"+goods.getPrice());
        System.out.println("库存："+goods.getStoage()+"件");
        System.out.println("销量："+goods.getSaleCount()+"件");
        System.out.println("是否要加入购物车：（yes/no）");
        String choice = sc.next();
        //判断选项
        if(choice.equals("yes")){
            System.out.println("请输入商品数量：");
            int count = sc.nextInt();
            if (count <= goods.getStoage() && count > 0){
                //添加购物车记录
                addShoppingCar(goods,count);
                //返回商品类别
                showGoodsClass();
            }
            else {
                System.out.println("库存不足！请重新选择！");
                showGoodsClass();
            }

        }
        //返回类别列表
        showGoodsClass();
    }

    /**
     * 添加商品之购物车
     * @param  goods
     * @param count
     */
    public void addShoppingCar(Goods goods,int count){
        //从数据库中根据登陆人找到对应的购物车集合
        ArrayList<ShoppingCar> carList = data.getCarData().get(loginUser.getUserId());
        //如果是第一次查找需要创建一个新的购物车集合
        if(carList == null){
            ArrayList<ShoppingCar> list = new ArrayList<>();
            //将新的集合写回数据库
            data.getCarData().put(loginUser.getUserId(),list);
        }
        //从数据库中根据登陆人找到对应的购物车集合
        carList = data.getCarData().get(loginUser.getUserId());
        //创建购物车集合
        ShoppingCar shoppingCar = new ShoppingCar(loginUser.getUserId(),goods.getGoodsId(),count);
        //判断购物记录是否存在于购物集合中的表示索引
        int index = -1;
        //对用户的购物车集合进行遍历
        for(int i = 0;i < carList.size();i++) {
            ShoppingCar car = carList.get(i);
            //判断购物车集合是否已经存在该商品
            if (car.getGoodsId() == goods.getGoodsId()) {
                //记录找到元素的索引
                index = i;
                break;
            }
            //如果不存在，则直接添加新的购物记录
        }
        //判断是否找到记录
        if(index != -1){
            //根据索引中到对应的购物记录
            ShoppingCar car = carList.get(index);
            //如果存在，则获取原有记录的商品数量
            int goodsCount = car.getSaleCount();
            //将原有记录的商品数量累加现在新增的商品数量
            goodsCount = goodsCount + count;
            //将修改后的数量写入购物记录；
            car.setSaleCount(goodsCount);
            //将修改后的记录写回集合
            carList.set(index,car);
        }
        else {
            //将记录添加至集合
            carList.add(shoppingCar);
        }
        //将集合写回数据库
        data.getCarData().put(loginUser.getUserId(),carList);
        System.out.println("已经添加至购物车");
    }
    /**
     *我的购物车
     */
    public void myShoppingCar(){
        System.out.println("\n--------购物车-------");
        //查找登陆人的购物车数据
        ArrayList<ShoppingCar> list = data.getCarData().get(loginUser.getUserId());
        if(list == null || list.size() == 0){
            System.out.println("购物车内没有商品！");
        }
        else {
            //合计总价
            double total = 0;
            //循环遍历购物车集合
            for (ShoppingCar car : list){
                //根据购物车中的商品编号获得商品对象
                Goods goods = getGoods(car.getGoodsId());
                //计算商品的总价
                double price = car.getSaleCount()*goods.getPrice();
                //累计合计总价
                total = total + price;
                if(car.getSaleCount() > goods.getStoage()){
                    System.out.println("你所购买的物品超过了该商品的库存，已经为您添加该商品所有库存件"+goods.getStoage());
                    car.setSaleCount(goods.getStoage());
                }
                System.out.println("商品名称:"+goods.getGoodsName()+"\t价格:"+goods.getPrice()+"\t数量:"+car.getSaleCount()+"\t总价:"+price);
            }
            System.out.println("合计:￥"+total);
            System.out.println("是否要结算：（yes/no）");
            String flag = sc.next();
            if(flag.equals("yes")){
                //支付结算
                pay();
            }
        }
        //返回主菜单
        mainMenu();
    }
    /**
     *支付结算
     */
    public void pay(){
        //获取登陆人的订单记录
        ArrayList<Order> orderlist = data.getOrderData().get(loginUser.getUserId());
        //判断是否有订单记录
        if (orderlist == null){
            //创建一个新的订单记录
            orderlist = new ArrayList<>();
            //写入数据库
            data.getOrderData().put(loginUser.getUserId(),orderlist);
        }
        //重新获取登陆人的订单记录
        orderlist = data.getOrderData().get(loginUser.getUserId());
        //获取登陆人的购物记录
        ArrayList<ShoppingCar> list = data.getCarData().get(loginUser.getUserId());
        //遍历登陆人的购物记录
        for (ShoppingCar car : list){
            //根据商品编号获得商品
            Goods goods = getGoods(car.getGoodsId());
            //根据商品的类别编号获得类别对象
            GoodsClass goodsClass = getGoodsClass(goods.getClassId());
            //生成系统时间
            Date now = new Date();
            //生成订单编号 使用商品类型的简写+商品编号+交易人编号+交易时间毫秒值单位
            String orderId = goodsClass.getSimpleClassName()+goods.getGoodsId()+loginUser.getUserId()+now.getTime();
            //根据购物车中的购物记录，生成订单对象
            Order order = new Order(orderId,loginUser.getUserId(),car.getGoodsId(),car.getSaleCount(),now);
            //将订单写入订单集合
            orderlist.add(order);
            int reduceStoage = goods.getStoage() - order.getSaleCount();
            goods.setStoage(reduceStoage);
            int sellcount = order.getSaleCount()+goods.getSaleCount();
            goods.setSaleCount(sellcount);
        }
        //将订单集合写入数据库
        data.getOrderData().put(loginUser.getUserId(),orderlist);
        //清空购物车
        data.getCarData().get(loginUser.getUserId()).clear();
        try {
            //以文件保存的方式打印购物小票
            FileWriter writer = new FileWriter(loginUser.getUserName()+"_购物小票.json");
            //使用FileWriter将订单数据写入文件
            for (Order order : orderlist){
                writer.write("交易时间："+dateUtil.parseDateToString(order.getSaleTime())+"\r\n");
                //获取商品
                Goods goods = getGoods(order.getGoodsId());
                writer.write("商品名称："+goods.getGoodsName()+"\r\n");
                writer.write("价格：￥"+goods.getPrice()+"\r\n");
                writer.write("数量"+order.getSaleCount()+"\r\n");
                writer.write("总价：￥"+order.getSaleCount()*goods.getPrice()+"\r\t");
            }
            //关闭数据流
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *根据商品编号获得商品对象
     * @param goodsId
     * @return Goods
     */
    public Goods getGoods(int goodsId){
        Goods goods = null;
        //遍历商品集合
        for (Goods g : data.getGoodsData()){
            //判断商品编号是否和集合中的商品的编号形同
            if(g.getGoodsId() == goodsId){
                //记录下当前商品
                goods = g;
                //退出循环
                break;
            }
        }
        return goods;
    }
    /**
     * 订单
     */
    public void myOrders(){
        System.out.println("----------我的订单---------");
        //获取登陆人的订单记录
        ArrayList<Order> orderList = data.getOrderData().get(loginUser.getUserId());
        if(orderList == null || orderList.size() == 0){
            System.out.println("订单是空的！");
        }
        else {
            //打印显示登陆人的订单数据
            for (Order order : orderList){
                System.out.println("交易时间："+dateUtil.parseDateToString(order.getSaleTime()));
                //根据商品编号查询商品对象
                Goods goods = getGoods(order.getGoodsId());
                System.out.println("商品名称："+goods.getGoodsName()+"\t价格：￥"+goods.getPrice()+"\t数量："+order.getSaleCount()+"\t总价：￥"+goods.getPrice()*order.getSaleCount());
            }
        }
        //返回主菜单
        mainMenu();
    }
}
