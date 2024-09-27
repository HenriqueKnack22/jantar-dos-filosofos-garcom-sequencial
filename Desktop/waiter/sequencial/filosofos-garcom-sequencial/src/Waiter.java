public class Waiter {
    private int semaphore = 0;
    private final int max;

    public Waiter(int init) {
        this.max = init;
    }

    public synchronized void v() throws InterruptedException {
        while (semaphore == max) {
            wait();
        }
        semaphore++;
    }

    public synchronized void p() throws InterruptedException {
        if (semaphore > 0) {
            notify();
        }
        semaphore--;
    }
}
