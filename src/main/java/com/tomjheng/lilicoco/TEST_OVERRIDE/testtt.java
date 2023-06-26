package com.tomjheng.lilicoco.TEST_OVERRIDE;

import java.lang.reflect.Method;
import java.util.Date;

//import ;
public class testtt {
	public <T> T doSomeThing() throws NoSuchMethodException, InstantiationException, IllegalAccessException {
		new Date();
		T t = (T) this.getClass().newInstance();
		System.out.println(this.getClass().getName());
		Class<? extends testtt> claz = this.getClass();
		System.out.println(claz.getPackage());
		Method mt = claz.getMethod("doSomeThing");


		return t;
	}
}
