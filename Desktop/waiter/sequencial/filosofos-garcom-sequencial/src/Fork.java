public class Fork {
    private boolean lock = false;

    public synchronized void vb() throws InterruptedException {
        while (lock) {
            wait();
        }
        lock = true;
        Thread.sleep(1000); // 1 segundo para pegar o garfo
    }

    public synchronized void pb() {
        if (lock) {
            notify();
        }
        lock = false;
    }
}
