package com.gustavo.distribute.mysql_distribute_lock;

public class GeneartorConsumer implements Runnable {
    private IdGenerator generator;

    public GeneartorConsumer(IdGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            generator.nextId();
        }
    }
}
