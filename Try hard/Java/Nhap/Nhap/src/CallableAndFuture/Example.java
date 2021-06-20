package CallableAndFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Example {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
// Create thread pool using Executor Framework
        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Future<Integer>> list = new ArrayList<Future<Integer>>();
        for (int i = 0; i < 10; i++) {
            // Create new Calculator object
            Calculator c = new Calculator(i, i + 1);

            list.add(executor.submit(c));
        }

        for (Future f : list) {
            System.out.println(f.get(3, TimeUnit.SECONDS));
        }

        executor.shutdown();
    }
}
