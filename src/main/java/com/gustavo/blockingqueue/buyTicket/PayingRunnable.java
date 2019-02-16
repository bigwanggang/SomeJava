package com.gustavo.blockingqueue.buyTicket;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 完成支付操作，首先模拟支付时间随机生成，支付完，从延迟队列里删除该任务，
 * 如果不删除一旦到了支付限定时间，会把该票重新设置为0
 * Created by gustaov on 2019/2/16.
 */
public class PayingRunnable implements Runnable {

    private final int buyerId;
    private final int ticketId;

    public PayingRunnable(int buyerId, int ticketId) {
        this.buyerId = buyerId;
        this.ticketId = ticketId;
    }

    @Override
    public void run() {
        Random random = new Random();
        int payingTime = random.nextInt(6000);
        System.out.println("buyer: " + buyerId + "， paying time: " + payingTime);
        try {
            TimeUnit.MILLISECONDS.sleep(payingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TicketManager.getInstance().removeTask(buyerId, ticketId);

    }
}
