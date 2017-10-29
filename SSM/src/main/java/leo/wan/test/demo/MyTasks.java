package leo.wan.test.demo;

import leo.wan.utils.DateHelper;

public class MyTasks {
	public void task1(){
		System.err.println("executor task1:"+DateHelper.getStringFullDate());
	}
	public void task2(){
		System.err.println("executor task2:"+DateHelper.getStringFullDate());
	}
}
