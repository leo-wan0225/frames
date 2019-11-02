package leo.wan.test.unit;

import org.junit.Test;

public class TestThread implements  Runnable{
    private  Integer count1 = 0;
    private static Integer count = 0;
    @Override
    public void run() {
        synchronized(count1){
            for(int i = 0 ; i < 5 ; i ++){
                System.out.println(Thread.currentThread().getName()+"> "+count++);
            }
        }
    }

    public static void main(String[] args) {
        /*Thread t1 = new Thread(new TestThread(),"th1");
        Thread t2 = new Thread(new TestThread(),"th2");
        Thread t3 = new Thread(new TestThread(),"th3");
        t1.start();
        t2.start();
        t3.start();*/
        for (int i=0;i<10;i++){
            Thread t = new Thread(new TestThread(),"th"+i);
            t.start();
        }
    }
}
