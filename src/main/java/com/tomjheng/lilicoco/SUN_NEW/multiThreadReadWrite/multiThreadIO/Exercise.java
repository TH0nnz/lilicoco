package com.tomjheng.lilicoco.SUN_NEW.multiThreadReadWrite.multiThreadIO;
        /*
        1、首先創建FileReader對象
        2、將FileReader傳遞給BufferedReader
        3、採用BufferedReader的readLine()方法和read()方法來讀取文件內容
        4、最後一定要的finally語句中關閉BufferedReaders
        5、FileReader與BufferedReader配合使用，File，FileInputStream，BufferedInputStream配合使用
        */

import java.io.*;

public class Exercise {

    public static void main(String args[]) {
        BufferedReader br = null;
        BufferedReader br2 = null;
        try {
            br = new BufferedReader(new FileReader("/home/zjz/Desktop/myfile.txt"));
            // The first way of reading the file
            System.out.println("Reading the file using readLine() method: ");
            String contentLine = br.readLine();
            while (contentLine != null) {
                System.out.println(contentLine);
                contentLine = br.readLine();
            }
            br2 = new BufferedReader(new FileReader("/home/zjz/Desktop/myfile2.txt"));
            // The second way of reading the file
            System.out.println("Reading the file using read() method: ");
            int num = 0;
            char ch;
            while ((num = br2.read()) != -1) {
                ch = (char) num;
                System.out.print(ch);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (br2 != null) {
                    br2.close();
                }
            } catch (IOException e) {
                System.out.println("Error in closing the BufferedReader");
            }
        }
    }
}
