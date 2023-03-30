package com.tomjheng.lilicoco.SUN_NEW.multiThreadReadWrite.FileLock3;

public class SecondThread implements Runnable {

    private WRFile wr = new WRFile();

    public SecondThread(WRFile wr) {
        this.wr = wr;
    }

    @Override
    public void run() {
//        while (true) {
//            wr.read2();
//        }
        for (int i = 0; i < 10; i++) {
            wr.read2();
        }
    }
}


