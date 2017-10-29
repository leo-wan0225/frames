package leo.wan.test.demo;

import org.junit.Test;

import leo.wan.test.base.BaseJunit4Test;

public class TestTask extends BaseJunit4Test{
		@Test
		public void test() throws InterruptedException{
			Thread.currentThread().sleep(1000*60*4);
		}
}
