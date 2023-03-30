package com.tomjheng.lilicoco.Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Reflection {

    

    private <T> T convertMap2Object(Class<T> target, HashMap<String, Object> inputMap) {
        // System.out.println(target.getClass().getName());
        T t = null;

        // 物件映射
        try {
            t = target.newInstance();
            //  取的target class
            Class<? extends Object> c = t.getClass();
            // 取的function 名稱 包含父類別
            Method[] m = c.getMethods();
            for (Method f : m) {
                if ("set".equals(f.getName().substring(0, 3))) {
                    // 取的set開頭function
                    String methodName = f.getName();
                    System.out.println(methodName);
                    Class<?> ParameterType = f.getParameterTypes()[0];
                    System.out.println(ParameterType);
                    String parameter = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                    System.out.println();

                    Method setNameMethode = t.getClass().getMethod(methodName, ParameterType);
                    setNameMethode.invoke(t, inputMap.get(parameter));

                }
            }
        } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException |
                 SecurityException | IllegalAccessException | InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }


    public static void main(String[] args) {

    }

}
