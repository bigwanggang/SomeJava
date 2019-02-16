package com.gustavo.blockingqueue.buyTicket;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayPayment implements Delayed {
    private final long latestPayTime;
    private final long time;
    private final int buyerId;
    private final int ticketId;

    public DelayPayment(long milliseconds, int buyerId, int ticketId) {
        this.ticketId = ticketId;
        this.buyerId = buyerId;
        this.latestPayTime = TimeUnit.NANOSECONDS.convert(milliseconds, TimeUnit.MILLISECONDS) + System.nanoTime();
        this.time = milliseconds;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.latestPayTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if (other == this)  // compare zero if same object
            return 0;

        if (other instanceof DelayPayment) {
            DelayPayment x = (DelayPayment) other;

            // 优先比较失效时间
            long diff = this.time - x.time;
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;

            else return 0;
        }

        // 一般不会执行到此处，除非元素不是Data类型
        long diff = this.getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS);
        return (diff < 0) ? -1 : (diff > 0) ? 1 : 0;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public int getTicketId() {
        return ticketId;
    }

    @Override
    public String toString() {
        return "DelayPayment{" +
                "latestPayTime=" + latestPayTime +
                ", time=" + time +
                ", buyerId=" + buyerId +
                ", ticketId=" + ticketId +
                '}';
    }
}
