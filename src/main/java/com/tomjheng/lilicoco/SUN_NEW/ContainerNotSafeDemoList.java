package com.tomjheng.lilicoco.SUN_NEW;

import java.util.*;

/***
 * 集合(List、Set、Map)線程不安全樣例及解決方案
 */
public class ContainerNotSafeDemoList {
    public static void main(String[] args) {
        //List場景
        //List list = new ArrayList();
        //List list = new Vector();解決方案1
        List list = Collections.synchronizedList(new ArrayList<>());//解決方案2
        list.add("synchronized");
        //解決方案3 CopyOnWriteArrayList
        //CopyOnWrite是寫時複製的容器，添加時首先對當前容器object[]拷貝得到object[] newElements
        //然後新容器裏面添加元素之後，將原容器引用指向新容器。
        //好處是可以併發的讀，不需要加鎖，因為當前容器不會添加任何元素，是一種讀寫分離思想
        //List list = new CopyOnWriteArrayList();

        //Set場景，
        //底層也是HashMap，其中set.add(key),其value存儲的是固定的對象PRESENT = new Object()
        //Set set = new HashSet();
        Set set = Collections.synchronizedSet(new HashSet<>());//解決方案1
        set.add("synchronized");
        //Set set = new CopyOnWriteArraySet();//解決方案2

        //Map場景
        //Map map = new HashMap();
        Map map = Collections.synchronizedMap(new HashMap());//解決方案1
        //Map map = new ConcurrentHashMap();//解決方案2
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
