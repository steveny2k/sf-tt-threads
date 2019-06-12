package queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UseBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<int[]> queue = new ArrayBlockingQueue<>(10);
        Thread prod = new Thread(() -> {
            try {
                System.out.println("Producer starting");
                int[] data;
                for (int i = 0; i < 400; i++) {
                    if (i < 50) Thread.sleep(10);
                    data = new int[]{i, 0};
                    Thread.sleep(1);
                    data[1] = i;
                    if (i == 300) data[0] = 299; // verify test!!!
                    queue.put(data); data = null;
                }
                System.out.println("Producer finished...");
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        });
        Thread cons = new Thread(() -> {
            try {
                System.out.println("Consumer starting...");
                for (int i = 0; i < 400; i++) {
                    int[] data = queue.take();
                    if (data[0] != i || data[1] != i) {
                        System.out.println("ERROR!!! at " + i);
                    }
                    if (i > 250) Thread.sleep(10);
                }
                System.out.println("Consumer exiting...");
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        });
        cons.start();
        prod.start();
        try {
            cons.join();
            prod.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
