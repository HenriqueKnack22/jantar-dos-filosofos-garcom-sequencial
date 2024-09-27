public class Main {
    public static void main(String[] args) throws InterruptedException {
        Waiter waiter = new Waiter(4);
        Fork[] forks = new Fork[5];
        String[] names = new String[]{"Aristoteles", "Socrates", "Platao", "Pascal", "Locke"};
        Philosopher[] phil = new Philosopher[5];

        // Criando os garfos
        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork();
        }

        // Criando os filósofos
        for (int i = 0; i < 5; i++) {
            phil[i] = new Philosopher(waiter, forks, i, names[i]);
        }

        // Executando os filósofos sequencialmente
        for (int i = 0; i < 5; i++) {
            phil[i].execute();
        }

        System.out.println("Mesa vazia.");
        System.out.println("Ordem de encerramento: " + String.join(", ", Philosopher.getOrderFinished()));
    }
}
