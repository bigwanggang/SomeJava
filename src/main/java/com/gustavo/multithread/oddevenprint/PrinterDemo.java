package com.gustavo.multithread.oddevenprint;

public class PrinterDemo {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread oddThread = new Thread(new OddPrinter(printer), "Odd-Thread");
        Thread evenThread = new Thread(new EvenPrinter(printer), "Even-Thread");
        oddThread.start();
        evenThread.start();
    }
}
