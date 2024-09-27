import java.util.ArrayList;
import java.util.List;

public class Philosopher {
    private final Fork[] forks;
    private final Waiter waiter;
    private final int id;
    private final String name;
    private static final int hunger = 3;
    private static final int sleepTime = 1000; // 1 segundo
    private static final int eatTime = 2000; // 2 segundos
    private static final int thinkTime = 1000; // 1 segundo
    private static final List<String> orderFinished = new ArrayList<>();
    private static final Object orderLock = new Object();

    public Philosopher(Waiter waiter, Fork[] forks, int id, String name) {
        this.waiter = waiter;
        this.forks = forks;
        this.id = id;
        this.name = name;
    }

    public static List<String> getOrderFinished() {
        return orderFinished;
    }

    public void think() throws InterruptedException {
        System.out.println(name + " está pensando.");
        Thread.sleep(thinkTime); // 1 segundo pensando
    }

    public void eat() throws InterruptedException {
        System.out.println(name + " está comendo.");
        Thread.sleep(eatTime); // 2 segundos comendo
    }

    public void execute() throws InterruptedException {
        System.out.println(name + " está sentado.");
        Thread.sleep(sleepTime); // 1 segundo para sentar

        for (int i = hunger; i > 0; i--) {
            System.out.println(name + " está com fome.");
            Thread.sleep(sleepTime); // 1 segundo para ficar com fome

            waiter.v(); // tenta pegar permissão para comer

            // Pegar garfos (espera 1 segundo)
            forks[id].vb();
            System.out.println(name + " pegou o garfo à sua esquerda.");
            forks[(id + 1) % 5].vb();
            System.out.println(name + " pegou o garfo à sua direita.");

            eat(); // Começa a comer

            // Soltar garfos (espera 1 segundo para devolver)
            forks[id].pb();
            System.out.println(name + " devolveu o garfo à sua esquerda.");
            forks[(id + 1) % 5].pb();
            System.out.println(name + " devolveu o garfo à sua direita.");

            waiter.p(); // Devolve permissão

            think(); // Começa a pensar
        }

        System.out.println(name + " está satisfeito.");
        Thread.sleep(sleepTime); // 1 segundo para sair da mesa
        System.out.println(name + " saiu da mesa.");

        synchronized (orderLock) {
            orderFinished.add(name); // Adiciona à lista de filósofos que terminaram
        }
    }
}
