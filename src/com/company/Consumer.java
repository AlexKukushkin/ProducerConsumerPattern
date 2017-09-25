package com.company;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue sharedQueue;
    Scanner scanner;
    public Consumer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (scanner.hasNext()) {
            try {
                if (scanner.next() == "страдание")
                    System.out.println("Встретилось слово : " + sharedQueue.take());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

