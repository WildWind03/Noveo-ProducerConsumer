package com.noveogroup.producer;

import com.noveogroup.buffer.BlockingQueue;
import com.noveogroup.data.Banana;
import org.apache.log4j.Logger;

public class Nature implements Producer {
    private final Logger logger = Logger.getLogger(Nature.class);

    private BlockingQueue<Banana> bananaTree;
    private final long period;

    public Nature(BlockingQueue<Banana> bananaTree, long period) {
        if (null == bananaTree) {
            throw new IllegalArgumentException("bananaTree can no be null");
        }

        if (period < 0) {
            throw new IllegalArgumentException("Period can not be negative");
        }

        this.bananaTree = bananaTree;
        this.period = period;
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()) {
                bananaTree.put(new Banana());
                logger.info("Nature has produced a new banana");
                System.out.println("+");
                Thread.sleep(period);
            }
        } catch (InterruptedException e) {
            logger.info("Interrupt");
        }
    }
}
