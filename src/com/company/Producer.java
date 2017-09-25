package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    String filename = "book1.txt";
    File file = new File(filename);
    Scanner scanner = new Scanner(file);

    private final BlockingQueue sharedQueue;

    public Producer(BlockingQueue sharedQueue) throws FileNotFoundException {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while(scanner.hasNext()){
            try {
                sharedQueue.put(scanner.next());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

