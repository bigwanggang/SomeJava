package com.gustavo.multithread.oddevenprint;

public class OddPrinter implements Runnable {
    private Printer printer;

    public OddPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            printer.printOdd();
        }
    }
}
