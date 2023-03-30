package com.tomjheng.lilicoco.SUN_NEW.multiThreadReadWrite.multiThreadIO;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class multiThreadIO {

    private static String d1 = "DoubleLinkList.java";
    private static String d2 = "HomeWorkLinked.java";

//    public static void main(String[] args) {
//        BufferedReader reader1 = null,reader2=null;
//        try {
//            reader1 = read(d1);
//            String str1 = null;
//            while ((str1 = reader1.readLine()) != null) {
//                System.out.println(str1);
//            }
//
//            reader2 = read(d2);
//            String str2 = null;
//            while ((str2 = reader2.readLine()) != null) {
//                System.out.println(str2);
//            }
//
//
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                reader1.close();
//                reader2.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }




//    public synchronized static BufferedReader read(String target) throws FileNotFoundException, UnsupportedEncodingException {
//        return new BufferedReader(new InputStreamReader(new FileInputStream("src/main/java/com/example/lilicoco/SUN_NEW/filepath/" + target), "UTF-8")); // 指定讀取文件的編碼格式，以免出現中文亂碼
//
//    }
    public synchronized static void rnw(String target) throws IOException {
        FileReader fr = new FileReader("/Users/tomjheng/IdeaProjects/lilicoco/lilicoco/src/main/java/com/example/lilicoco/SUN_NEW/filepath/"+target);
        FileReader ofr = new FileReader("/Users/tomjheng/IdeaProjects/lilicoco/lilicoco/src/main/java/com/example/lilicoco/SUN_NEW/filepath/out.txt");

        FileWriter fw = new FileWriter("/Users/tomjheng/IdeaProjects/lilicoco/lilicoco/src/main/java/com/example/lilicoco/SUN_NEW/filepath/out.txt");
        BufferedReader bufr = new BufferedReader(fr);
        BufferedReader obufr = new BufferedReader(ofr);

        BufferedWriter bufw = new BufferedWriter(fw);
        String line = null;
        String oline = null;

        while((oline = obufr.readLine()) != null){
            bufw.write(oline);
            bufw.newLine();
            bufw.flush();
        }


        while((line = bufr.readLine()) != null){
            bufw.write(line);
            bufw.newLine();
            bufw.flush();
        }
        int ch = 0;
        while((ch = bufr.read())!=-1){
            bufw.write(ch);
        }
        bufr.close();
        obufr.close();
        bufw.close();
    }
    public static void main(String[] args) throws IOException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        rnw(index%2==0?d1:d2);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

//        FileReader fr = new FileReader("/Users/tomjheng/IdeaProjects/lilicoco/lilicoco/src/main/java/com/example/lilicoco/SUN_NEW/filepath/DoubleLinkList.java");
//        FileWriter fw = new FileWriter("/Users/tomjheng/IdeaProjects/lilicoco/lilicoco/src/main/java/com/example/lilicoco/SUN_NEW/filepath/out.txt");
//        BufferedReader bufr = new BufferedReader(fr);
//        BufferedWriter bufw = new BufferedWriter(fw);
//        String line = null;
//        while((line = bufr.readLine()) != null){
//            bufw.write(line);
//            bufw.newLine();
//            bufw.flush();
//        }
//        int ch = 0;
//        while((ch = bufr.read())!=-1){
//            bufw.write(ch);
//        }
//        bufr.close();
//        bufw.close();
    }
}
