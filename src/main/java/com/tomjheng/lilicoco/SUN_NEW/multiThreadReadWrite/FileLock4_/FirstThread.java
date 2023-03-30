package com.tomjheng.lilicoco.SUN_NEW.multiThreadReadWrite.FileLock4_;

public class FirstThread implements Runnable {

    private WRFile wr = new WRFile();

    public FirstThread(WRFile wr) {
        this.wr = wr;
    }

    @Override
    public void run() {
//        while (true) {
            wr.read1();
//        }
    }
}
