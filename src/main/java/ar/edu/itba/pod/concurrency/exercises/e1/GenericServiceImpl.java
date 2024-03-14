package ar.edu.itba.pod.concurrency.exercises.e1;

import java.util.*;

/**
 * Basic implementation of {@link GenericService}.
 */
public  class GenericServiceImpl implements GenericService {

    private int visitCount = 0;
    private final Object visitLock = new Object();

    private final Queue<String> queue = new LinkedList<>();

    @Override
    public String echo(String message) {
        return message;
    }

    @Override
    public String toUpper(String message) {
        if(message == null){
            return null;
        }
        return message.toUpperCase();

        // Alternativa
        // return Optional.ofNullable(message).map(m -> m.toUpperCase()).orElse(null);
    }

    @Override
    public void addVisit() {
        synchronized (visitLock) {
            this.visitCount++;
        }
    }

    @Override
    public int getVisitCount() {
        synchronized (visitLock) {
            return this.visitCount;
        }
    }

    @Override
    public boolean isServiceQueueEmpty() {
        synchronized (queue) {
            return queue.isEmpty();
        }
    }

    @Override
    public void addToServiceQueue(String name) {
        synchronized (queue) {
            this.queue.add(name);
        }
    }

    @Override
    public String getFirstInServiceQueue() {
        if(isServiceQueueEmpty()) {
            throw new IllegalStateException("No one in queue");
        }
        synchronized (queue) {
            return this.queue.remove();
        }
    }
}
