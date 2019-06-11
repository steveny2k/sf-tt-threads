package runnables;

class MyJob implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " Value of i is " + i);
        }
    }
}

public class Simpl {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Runnable r = new MyJob();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        System.out.println(Thread.currentThread().getName() + " exiting...");
    }
}
