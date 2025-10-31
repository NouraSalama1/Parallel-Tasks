public class DefaultHandlerExample {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("Global handler caught exception in: " +
                    thread.getName());
            System.out.println("Error: " + exception.getMessage());
        });

        Thread t1 = new Thread(() -> {
            throw new RuntimeException("Thread crashed!");
        });

        Thread t2 = new Thread(() -> {
            throw new ArithmeticException("Division by zero!");
        });

        t1.start();
        t2.start();
    }
}
class Worker extends Thread {
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " started.");
            int result = 10 / 0; // This will throw ArithmeticException
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " caught: " + e);
        }
        System.out.println(Thread.currentThread().getName() + " finished.");
    }
}

public class MultiThreadExceptionExample {
    public static void main(String[] args) {
        Worker t1 = new Worker();
        Worker t2 = new Worker();
        Worker t3 = new Worker();

        t1.start();
        t2.start();
        t3.start();
    }
}
class WorkerThread extends Thread {
    public void run() {
        // No try-catch here
        System.out.println("Thread started: " + getName());
        int x = 10 / 0; // This throws ArithmeticException
    }
}

public class Example_UncaughtHandler {
    public static void main(String[] args) {
        WorkerThread t1 = new WorkerThread();

        t1.setUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("⚠ Exception in " + thread.getName() + ": " +
                    exception.getMessage());
        });

        t1.start();
    }
}
public public class RaceConditionExample {

    static int counter = 0; // Shared resource

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyTask(), "Thread-1");
        Thread t2 = new Thread(new MyTask(), "Thread-2");

        t1.start();
        t2.start();
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                int current = counter;
                try {
                    // Small delay to increase overlap
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
 {
    
}
 int updated = ++counter;
                System.out.println(Thread.currentThread().getName()
                        + " -> Current: " + current + ", Updated: " + updated);
            }
        }
    }
}
