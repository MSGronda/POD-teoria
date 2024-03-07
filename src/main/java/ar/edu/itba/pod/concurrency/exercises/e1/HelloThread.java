package ar.edu.itba.pod.concurrency.exercises.e1;

public class HelloThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from a thread!");
    }
    public static void main(String[] args) {
        Thread thread = new HelloThread();
        System.out.println("Antes del thread");
        thread.start();
        System.out.println("Despues del thread");
    }
}
