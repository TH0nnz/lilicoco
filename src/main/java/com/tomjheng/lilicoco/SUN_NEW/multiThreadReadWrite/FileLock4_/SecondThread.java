package com.tomjheng.lilicoco.SUN_NEW.multiThreadReadWrite.FileLock4_;

public class SecondThread implements Runnable {

    private WRFile wr = new WRFile();

    public SecondThread(WRFile wr) {
        this.wr = wr;
    }

    @Override
    public void run() {
//        while (true) {
            wr.read2();
//        }
    }
}


