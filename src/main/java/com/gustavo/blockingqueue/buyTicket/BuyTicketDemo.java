package com.gustavo.blockingqueue.buyTicket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BuyTicketDemo {

    public static final int BUYERID = 20;

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < BUYERID; i++) {
            int ticketId = i % TicketManager.TICKET_NUM;
            DelayPayment payment = new DelayPayment(TicketManager.DELAYTIME, i, ticketId);
            if (TicketManager.getInstance().buyTicket(i, ticketId)) {
                System.out.println("buyer: " + i + ", ticketId: " + ticketId + " success. ");
                TicketManager.getInstance().addDelayPayment(payment);
                service.submit(new PayingRunnable(i, ticketId));
            }
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] ticketInfo = TicketManager.TICKET_INFO;
        int len = ticketInfo.length;
        for (int i = 0; i < len; i++) {
            System.out.println("ticket: " + i + " buyer: " + ticketInfo[i]);
        }

    }
}
