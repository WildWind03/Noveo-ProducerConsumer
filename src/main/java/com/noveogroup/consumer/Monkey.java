package com.noveogroup.consumer;

import com.noveogroup.buffer.BlockingQueue;
import com.noveogroup.data.Banana;
import org.apache.log4j.Logger;

public class Monkey implements Consumer {
    private final Logger logger = Logger.getLogger(Monkey.class);

    private BlockingQueue<Banana> bananaTree;
    private final long period;

    public Monkey(BlockingQueue<Banana> bananaTree, long period) {
        if (null == bananaTree) {
            throw new IllegalArgumentException("Banana tree MUST EXIST!");
        }

        if (period < 0) {
            throw new IllegalArgumentException("Period can not be negative");
        }

        this.period = period;
        this.bananaTree = bananaTree;
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()) {
                bananaTree.pop();
                logger.info("A monkey has eaten a banana");
                System.out.println("-");
                Thread.sleep(period);
            }
        } catch (InterruptedException e) {
            logger.info("Interrupt");
        }
    }
}
