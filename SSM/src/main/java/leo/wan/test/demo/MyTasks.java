package leo.wan.test.demo;

import leo.wan.utils.DateUtils;

public class MyTasks {
	public void task1(){
		System.err.println("executor task1:"+DateUtils.getStringFullDate());
	}
	public void task2(){
		System.err.println("executor task2:"+DateUtils.getStringFullDate());
	}
}
