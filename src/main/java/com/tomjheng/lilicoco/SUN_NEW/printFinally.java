package com.tomjheng.lilicoco.SUN_NEW;




public class printFinally {
    public static void main(String[] args) {
        System.out.println(printw());
    }
    public static String printw() {
        try {
            if (1==1) {
                throw new RuntimeException();
            }
            System.out.println("trytry");

            return "try";
        } catch (Exception ex) {
            System.out.println("catchcatch");
            return "catch";
        } finally {
            System.out.println("finallyfinally");

            return "finally";
        }
    }

}
