package com.tomjheng.lilicoco.SUN_NEW.cache;

import java.util.ArrayList;

public class test {
     static ArrayList<Object> MyData = new ArrayList<Object>();

    public static void main(String[] args) {
        ECache s = new ECache();
        MyData.add("1111");
        MyData.add("33333");
        MyData.add("22222");

        s.put("MyData",MyData);


        System.out.println(s.get("MyData").toString());

    }
}
