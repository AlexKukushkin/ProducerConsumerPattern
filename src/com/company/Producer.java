package com.company;

import java.io.*;
//import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private String filename;
    public Consumer consumer;

    public Producer(Consumer consumer, String filename) {
        this.consumer = consumer;
        this.filename = filename;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
                if (line != null) {
                    try {
                        consumer.addStringRow(line);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        consumer.setFileIsFinished();
    }
}

