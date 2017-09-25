package com.company;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<>();
    private static volatile boolean fileIsFinished = false;
    private static final String myString = "страдание";
    private static volatile int allEntryWords = 0;

    public void setFileIsFinished() {
        fileIsFinished = true;
    }

    public void addStringRow(String s) throws InterruptedException {
        sharedQueue.put(s);
    }

    @Override
    public void run() {
        while (!fileIsFinished || sharedQueue.size() != 0) {
            if (sharedQueue.size() != 0) {
                String str = new String(sharedQueue.poll());
                if (str != null) {
                    int lastIndex = 0;
                    int count = 0;
                    while (lastIndex != -1) {
                        lastIndex = str.indexOf(myString, lastIndex);
                        if (lastIndex != -1) {
                            count++;
                            lastIndex += myString.length();
                        }
                    }
                    allEntryWords = allEntryWords + count;
                    System.out.println("Число вхождений слова - " + myString + " - равно : " + allEntryWords);
                }
            }
        }
    }
}

