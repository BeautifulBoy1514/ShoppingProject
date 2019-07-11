package com.igeek.app;

import com.igeek.service.ShoppingSystem;

public class RunApplication {
    public static void main(String[] args) {
        ShoppingSystem sys = new ShoppingSystem();
        //开启系统
        sys.start();
    }
}
