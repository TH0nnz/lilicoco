package com.tomjheng.lilicoco.SUN_NEW;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class set {
    public static void main(String[] args) {
        Object Integer;
        Set testSet = new HashSet<Object>();

        testSet.add("SET");
        testSet.add("SET1");
        testSet.add(null);
        testSet.add(null);
        testSet.add(null);

        System.out.println(testSet.toArray());
        ArrayList a = new ArrayList();

        a.add(null);
        a.add(null);
        a.add(null);
        a.add("1");
        a.add("1");
        a.add("1");
        System.out.println(a);
        System.out.println(a);

    }
}
