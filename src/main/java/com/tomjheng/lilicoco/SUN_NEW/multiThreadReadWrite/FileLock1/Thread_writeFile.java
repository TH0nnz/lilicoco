package com.tomjheng.lilicoco.SUN_NEW.multiThreadReadWrite.FileLock1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Calendar;
/**
 * @author chb
 */
public class Thread_writeFile extends Thread{
    public void run(){
        Calendar calstart=Calendar.getInstance();
        File file=new File("/Users/tomjheng/IdeaProjects/outputFile/outputFile.txt");
        try {
            if(!file.exists())
                file.createNewFile();

            //對該文件加鎖
            RandomAccessFile out = new RandomAccessFile(file, "rw");
            FileChannel fcout=out.getChannel();
            FileLock flout=null;
            while(true){
                try {
                    flout = fcout.tryLock();
                    break;
                } catch (Exception e) {
                    System.out.println("有其他線程正在操作該文件，當前線程休眠1000毫秒");
                    sleep(1000);
                }

            }

            for(int i=1;i<=1000;i++){
                sleep(10);
                StringBuffer sb=new StringBuffer();
                sb.append("這是第"+i+"行，應該沒啥錯哈 ");

                out.write(sb.toString().getBytes("utf-8"));
            }

            flout.release();
            fcout.close();
            out.close();
            out=null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Calendar calend=Calendar.getInstance();
        System.out.println("寫文件共花了"+(calend.getTimeInMillis()-calstart.getTimeInMillis())+"秒");
    }
}
