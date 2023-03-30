package com.tomjheng.lilicoco.SUN_NEW.multiThreadReadWrite.FileLock3;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * https://www.jb51.net/article/168818.htm
 */
public class Demo {
    //ThreadPool
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 7;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {

        //創建數據對象
        WRFile wr = new WRFile();

        //設置和獲取類
        FirstThread ft = new FirstThread(wr);
        SecondThread st = new SecondThread(wr);
        //線程類
        Thread th1 = new Thread(ft);
        Thread th2 = new Thread(st);
        //啟動線程
        th1.start();
        th2.start();

        //ThreadPool
        //使用阿里巴巴推薦的創建線程池的方式
        //通過ThreadPoolExecutor構造函數自定義參數創建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

//        for (int i = 0; i < 10; i++) {
//        //創建WorkerThread對象（WorkerThread類實現了Runnable 接口）
////            Runnable worker = new MyRunnable("" + i);
//        //執行Runnable
//        executor.execute(new SecondThread(wr));
//        executor.execute(new FirstThread(wr));
//        }
//        //終止線程池
//        executor.shutdown();
//        while (!executor.isTerminated()) {
//        }
        System.out.println("Finished all threads");
    }


}


