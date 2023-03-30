package com.tomjheng.lilicoco.SUN_NEW.multiThreadReadWrite.FileLock2;

import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: emp_customer
 * @Package: PACKAGE_NAME
 * @ClassName: Test
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2019/10/11 14:10
 * @Version: 1.0
 * 多個線程同時寫同一個文件
 *  用來讀取大檔 先拆開 後寫入
 * https://blog.csdn.net/bird_tp/article/details/102504833?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-0-102504833-blog-114058409.pc_relevant_default&spm=1001.2101.3001.4242.1&utm_relevant_index=3
 */
public class Test {
    public static void main(String args[]){

        //線程數
        int threadSize=4;
        //源文件地址
        String sourcePath = "/Users/tomjheng/IdeaProjects/inputFile/InputFile1.java";
        //目標文件地址
        String destnationPath = "/Users/tomjheng/IdeaProjects/outputFile/outputFile.txt";
        //
        CountDownLatch latch = new CountDownLatch(threadSize);
        MultiDownloadFileThread m = new MultiDownloadFileThread(threadSize, sourcePath, destnationPath, latch);
        long startTime = System.currentTimeMillis();
        try {
            m.excute();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("全部下載結束,共耗時" + (endTime - startTime) / 1000 + "s");
    }

}

