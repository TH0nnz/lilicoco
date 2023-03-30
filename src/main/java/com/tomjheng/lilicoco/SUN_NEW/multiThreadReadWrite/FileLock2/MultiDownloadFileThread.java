package com.tomjheng.lilicoco.SUN_NEW.multiThreadReadWrite.FileLock2;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: emp_customer
 * @Package: PACKAGE_NAME
 * @ClassName: MultiDownloadFileThread
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2019/10/11 15:03
 * @Version: 1.0
 */
public class MultiDownloadFileThread {

    private int threadCount;
    private String sourcePath;
    private String targetPath;
    private CountDownLatch latch;

    public MultiDownloadFileThread(int threadCount, String sourcePath, String targetPath, CountDownLatch latch) {
        this.threadCount = threadCount;
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
        this.latch = latch;
    }

    public void excute() {
        File file = new File(sourcePath);
        int fileLength = (int) file.length();
        //分割文件
        int blockSize = fileLength / threadCount;
        for (int i = 1; i <= threadCount; i++) {
            //第一個線程下載的開始位置
            int startIndex = (i - 1) * blockSize;
            int endIndex = startIndex + blockSize - 1;
            if (i == threadCount) {
                //最後一個線程下載的長度稍微長一點
                endIndex = fileLength;
            }
            System.out.println("線程" + i + "下載:" + startIndex + "字節~" + endIndex + "字節");
            new DownLoadThread(i, startIndex, endIndex).start();
        }
    }


    public class DownLoadThread extends Thread {
        private int i;
        private int startIndex;
        private int endIndex;

        public DownLoadThread(int i, int startIndex, int endIndex) {
            this.i = i;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            File file = new File(sourcePath);
            FileInputStream in = null;
            RandomAccessFile raFile = null;
            FileChannel fcin = null;
            FileLock flin = null;
            try {
                in = new FileInputStream(file);
                in.skip(startIndex);
                //給要寫的文件加鎖
                raFile = new RandomAccessFile(targetPath, "rwd");
                fcin =raFile.getChannel();
                while(true){
                    try {
                        flin = fcin.tryLock();
                        break;
                    } catch (Exception e) {
                        System.out.println("有其他線程正在操作該文件，當前線程休眠1000毫秒,當前進入的線程為："+i);
                        sleep(1000);
                    }
                }
                //隨機寫文件的時候從哪個位置開始寫
                raFile.seek(startIndex);
                int len = 0;
                byte[] arr = new byte[1024];
                //獲取文件片段長度
                int segLength = endIndex - startIndex + 1;
                while ((len = in.read(arr)) != -1) {
                    if (segLength > len) {
                        segLength = segLength - len;
                        raFile.write(arr, 0, len);
                    } else {
                        raFile.write(arr, 0, segLength);
                        break;
                    }
                }
                System.out.println("線程" + i + "下載完畢");
                //計數值減一
                latch.countDown();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (raFile != null) {
                        raFile.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
