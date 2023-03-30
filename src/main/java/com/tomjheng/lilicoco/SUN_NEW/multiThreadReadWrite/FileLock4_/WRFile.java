package com.tomjheng.lilicoco.SUN_NEW.multiThreadReadWrite.FileLock4_;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WRFile {

    boolean flag;

    public WRFile() {
    }

    public synchronized void read1() {

        if (this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile ra = null;

        RandomAccessFile fis = null;


        try {
            File oFile = new File("/Users/tomjheng/IdeaProjects/inputFile/InputFile1.java");
            fis = new RandomAccessFile(oFile, "rw");
            byte[] buf = new byte[1024];
            StringBuffer sb = new StringBuffer();
            while ((fis.read(buf)) != -1) {
                sb.append(new String(buf, "utf-8"));
                buf = new byte[1024];
            }
            System.out.println(sb);

            ra = new RandomAccessFile("/Users/tomjheng/IdeaProjects/outputFile/outputFile.txt", "rw");

            ra.seek(ra.length());

            ra.write(sb.toString().getBytes("utf-8"));
//          ra.writeBytes("input");
            ra.writeBytes("\r\n");
            ra.writeBytes("read1 end");
            ra.writeBytes("\r\n");
            ra.writeBytes("\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                ra.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //修改標記 喚醒線程
        this.flag = true;
        this.notify();

        System.out.println("read1 end");
    }

    public synchronized void read2() {

        if (!this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        RandomAccessFile ra = null;

        RandomAccessFile fis = null;

        try {
            File oFile = new File("/Users/tomjheng/IdeaProjects/inputFile/InputFile2.java");
            fis = new RandomAccessFile(oFile, "rw");
            byte[] buf = new byte[1024];
            StringBuffer sb = new StringBuffer();
            while ((fis.read(buf)) != -1) {
                sb.append(new String(buf, "utf-8"));
                buf = new byte[1024];
            }
            System.out.println(sb);

            ra = new RandomAccessFile("/Users/tomjheng/IdeaProjects/outputFile/outputFile.txt", "rw");
            //不覆蓋已存在行
            ra.seek(ra.length());

            ra.write(sb.toString().getBytes("utf-8"));
            ra.writeBytes("\r\n");
            ra.writeBytes("read2 end");
            ra.writeBytes("\r\n");
            ra.writeBytes("\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ra.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //修改標記 喚醒線程
        this.flag = false;
        this.notify();

        System.out.println("read2 end");

    }
}
