package com.noveogroup.main;

import com.noveogroup.buffer.BlockingQueue;
import com.noveogroup.consumer.Monkey;
import com.noveogroup.data.Banana;
import com.noveogroup.producer.Nature;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    /**
     * type 'stop' to stop the program
     **/
    public static void main(String[] args) {
        try {
            BlockingQueue<Banana> bananaBlockingQueue = new BlockingQueue<>(5);
            Thread consumerThread = new Thread(new Monkey(bananaBlockingQueue, 5000L));
            Thread producerThread = new Thread(new Nature(bananaBlockingQueue, 3000L));

            consumerThread.start();
            producerThread.start();


            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {
                    if ("stop".equals(br.readLine())) {
                        System.out.println("Exit. OK");
                        System.out.flush();
                        consumerThread.interrupt();
                        producerThread.interrupt();
                        break;
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
