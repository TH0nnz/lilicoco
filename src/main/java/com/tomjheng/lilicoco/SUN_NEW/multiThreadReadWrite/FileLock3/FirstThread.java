package com.tomjheng.lilicoco.SUN_NEW.multiThreadReadWrite.FileLock3;

public class FirstThread implements Runnable {

    private WRFile wr = new WRFile();

    public FirstThread(WRFile wr) {
        this.wr = wr;
    }

    @Override
    public void run() {
//        while (true) {
//            wr.read1();
//        }
        for (int i = 0; i < 10; i++) {
            wr.read1();
        }

    }
}
