package com.gustavo.multithread.oddevenprint;

public class EvenPrinter implements Runnable {
    private Printer printer;

    public EvenPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            printer.printEven();
        }
    }
}
