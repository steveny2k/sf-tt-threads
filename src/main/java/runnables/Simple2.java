package runnables;

class MyJob2 implements Runnable {
    private int i = 0; // shared data!!!!

    @Override
    public void run() {
        for (; i < 10_000; i++) {
            System.out.println(Thread.currentThread().getName() + " Value of i is " + i);
        }
    }
}

public class Simple2 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Runnable r = new MyJob2();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        System.out.println(Thread.currentThread().getName() + " exiting...");
    }
}
