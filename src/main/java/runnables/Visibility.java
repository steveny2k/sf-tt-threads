package runnables;

class Stopper implements Runnable {
    private Visibility vis;

    public Stopper(Visibility vis) {
        this.vis = vis;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " starting....");
        while(!vis.stop)
            ;

        System.out.println(Thread.currentThread().getName() + " exiting...");
    }
}
public class Visibility {
    public volatile boolean stop = false;

    public void go() {
        Stopper stopper = new Stopper(this);
        new Thread(stopper).start();
        System.out.println("thread started...");
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop = true;
        System.out.println("stop flag set, exiting");
    }

    public static void main(String[] args) {
        new Visibility().go();
        System.out.println("main exiting...");
    }
}
