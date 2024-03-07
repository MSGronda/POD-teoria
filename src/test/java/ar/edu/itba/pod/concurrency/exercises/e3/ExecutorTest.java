package ar.edu.itba.pod.concurrency.exercises.e3;

import ar.edu.itba.pod.concurrency.exercises.e1.GenericService;
import ar.edu.itba.pod.concurrency.exercises.e1.GenericServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExecutorTest {
    private GenericService service;

    public class Task implements Callable<Integer> {

        @Override
        public Integer call() {
            for(int i=0; i<5; i++){
                service.addVisit();
            }
            return service.getVisitCount();
        }
    }

    @BeforeEach
    public final void before() {
        service = new GenericServiceImpl();
    }

    @Test
    public final void test() throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Future<Integer> asyncTask = executorService.submit(new Task());

        assertEquals(5, asyncTask.get(100, TimeUnit.MILLISECONDS));
    }

    @Test
    public final void test2() throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Future<Integer> asyncTask = executorService.submit(new Task());

        asyncTask.get(100, TimeUnit.MILLISECONDS);

        assertEquals(5, service.getVisitCount());
    }
}
