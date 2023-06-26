package com.tomjheng.lilicoco.TEST_OVERRIDE;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class BreakLoop {
	public static void main(String[] args) {
		int num = 0;
		int num2 = 0;
		System.out.println(Thread.currentThread().getId());
		while (true) {
			//CAS加鎖
			if (num == 3) {
				//換得過去就加鎖成功
				//執行
				//CAS解鎖
				if (num2 == 3) {
					//換得回來就出loop
					break;
				}

			}
			num++;
			num2++;

		}
	}
}