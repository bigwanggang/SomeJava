package com.gustavo.blockingqueue.buyTicket;

import java.util.concurrent.DelayQueue;

public class TicketManager {
    public static final int TICKET_NUM = 10;
    public static final int DELAYTIME = 3000;
    public static final int[] TICKET_INFO = new int[TICKET_NUM];

    static {
        for (int i = 0; i < TICKET_NUM; i++) {
            TICKET_INFO[i] = -1;
        }
    }

    private static Thread thread;

    private static DelayQueue<DelayPayment> delayPayments = new DelayQueue<>();
    private static TicketManager ticketManager = new TicketManager();

    public static TicketManager getInstance() {
        return ticketManager;
    }

    public void removeTask(int buyerId, int ticketId) {
        for (DelayPayment delayPayment : delayPayments) {
            if (ticketId == delayPayment.getTicketId()) {
                delayPayments.remove(delayPayment);
                System.out.println("buyerId: " + buyerId + " ticketId: " + ticketId + " remove delayqueue");
            }
        }
    }

    public void addDelayPayment(DelayPayment delayPayment) {
        delayPayments.add(delayPayment);
    }

    private TicketManager() {
        thread = new Thread(new PaymentRunnable());
        thread.setDaemon(true);
        thread.start();
    }

    public boolean buyTicket(int buyerId, int ticketId) {
        if (ticketId < 0 || ticketId > TICKET_NUM)
            return false;
        if(TICKET_INFO[ticketId] != -1){
            return false;
        }
        TICKET_INFO[ticketId] = buyerId;
        return true;
    }

    static class PaymentRunnable implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    DelayPayment payment = delayPayments.take();
                    System.out.println("buyer: " + payment.getBuyerId() + " does not pay in certain time, ticketId: " + payment.getTicketId());
                    TICKET_INFO[payment.getTicketId()] = -1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
