import java.util.List;

public class MultiExecutor {
    private List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll() {
        for (Runnable task : tasks) {
            Thread t = new Thread(task);
            t.start();
        }
    }

    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("Task 1 running: " + Thread.currentThread().getName());
        Runnable r2 = () -> System.out.println("Task 2 running: " + Thread.currentThread().getName());
        Runnable r3 = () -> System.out.println("Task 3 running: " + Thread.currentThread().getName());

        List<Runnable> list = List.of(r1, r2, r3);
        MultiExecutor m = new MultiExecutor(list);
        m.executeAll();
    }
}
