package com.tomjheng.lilicoco.SUN_NEW.multiThreadReadWrite.FileLock1;

/**
 * @author Administrator
 *
 * https://blog.csdn.net/gxy3509394/article/details/7435993
 *
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Thread_writeFile thf3=new Thread_writeFile();
        Thread_readFile thf4=new Thread_readFile();
        thf3.start();
        thf4.start();

    }

}
