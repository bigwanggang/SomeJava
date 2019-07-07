package com.gustavo;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        System.out.println(Integer.numberOfLeadingZeros(1));
        System.out.println(Integer.numberOfLeadingZeros(-1));

        AtomicInteger i = new AtomicInteger();
        System.out.println(i.get());
    }
}
