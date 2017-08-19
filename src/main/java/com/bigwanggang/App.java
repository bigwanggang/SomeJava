package com.bigwanggang;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream in = new FileInputStream("d://1.xml");
        XMLStreamReader reader = factory.createXMLStreamReader(in, "UTF-8");
        while (reader.hasNext()) {
            int point = reader.next();
            if (point == XMLStreamReader.START_ELEMENT) {
                int count = reader.getAttributeCount();
                String name = reader.getName().toString();
                System.out.println("start element:" + name);
                for (int i = 0; i < count; i++) {
                    System.out.print(reader.getAttributeName(i) + ":" + reader.getAttributeValue(i) + "--");
                }
                System.out.println();
            } else if (point == XMLStreamReader.END_ELEMENT) {
                String name = reader.getName().toString();
                System.out.println("end element:" + name);
            }
        }

    }
}
