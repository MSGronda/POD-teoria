package ar.edu.itba.pod.concurrency.exercises.e2;

import ar.edu.itba.pod.concurrency.exercises.e1.GenericService;
import ar.edu.itba.pod.concurrency.exercises.e1.GenericServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ThreadTest {
    private GenericService service;

    public class VisitThread extends Thread {
        @Override
        public void run() {
            for(int i=0; i<5; i++) {
                service.addVisit();
            }
        }
    }

    public class VisitRunnable implements Runnable{
        @Override
        public void run() {
            for(int i=0; i<5; i++) {
                service.addVisit();
            }
        }
    }

    @BeforeEach
    public final void before() {
        service = new GenericServiceImpl();
    }
    @Test
    public final void testThread() throws InterruptedException {
        final Thread test = new VisitThread();
        test.start();

        test.join(100);

        assertEquals(5, service.getVisitCount());
    }


    @Test
    public final void testRunnable() throws InterruptedException {
        final Thread test = new Thread(new VisitRunnable());
        test.start();

        test.join(100);

        assertEquals(5, service.getVisitCount());
    }

    @Test
    public final void testLambda() throws InterruptedException {
        final Thread test = new Thread(() -> {
            for(int i=0; i<5; i++) {
                service.addVisit();
            }
        });
        test.start();

        test.join(100);

        assertEquals(5, service.getVisitCount());
    }
}
