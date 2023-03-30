package com.tomjheng.lilicoco.SUN_NEW.ThreadLocal;

public class UseThreadLocal {
    public static void main(String[] args) {
        RESOURCE.set("主線程");
        System.out.println("線程名："+Thread.currentThread().getName()+"----"+RESOURCE.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                RESOURCE.set("a線程");
                System.out.println("線程名："+Thread.currentThread().getName()+"----"+RESOURCE.get());

            }
        },"[a]線程").start();

    }
    public final static ThreadLocal RESOURCE = new ThreadLocal<String>();

}
