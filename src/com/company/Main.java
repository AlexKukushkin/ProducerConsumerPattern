package com.company;
import java.io.FileNotFoundException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {
        String filename = "book1.txt";
        BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<>();

        Consumer consumer = new Consumer();
        Producer producer = new Producer(consumer, filename);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
