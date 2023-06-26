package com.tomjheng.lilicoco.TEST_OVERRIDE;

import java.util.concurrent.atomic.AtomicInteger;

public class CAS_test {
	public static void main(String[] args) {
		final int oragenNum = 0;
		AtomicInteger atomicInteger = new AtomicInteger(oragenNum);

		int value = atomicInteger.get();
		int threadNum = (int) Thread.currentThread().getId();
//		 非阻塞 等待鎖
		while (true) {
			//CAS加鎖
			if (atomicInteger.compareAndSet(value, threadNum)) {
				//換得過去就加鎖成功

				//CAS解鎖
				if (atomicInteger.compareAndSet(atomicInteger.get(), oragenNum)) {
					//換得回來就出loop
					break;
				}

			}
		}
		//執行
	}
}
