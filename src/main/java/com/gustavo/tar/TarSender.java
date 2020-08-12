package com.gustavo.zip;

import com.csvreader.CsvWriter;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

public class TarSender {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 9997);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = "hello1".getBytes();
        byte[] bytes1 = "hello1".getBytes();
        try {
            TarArchiveOutputStream tos = new TarArchiveOutputStream(socket.getOutputStream());
//            tos.
//            one file
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CsvWriter csvWriter = new CsvWriter(baos, ',', Charset.forName("GBK"));
            String[] data = {"hello", "world"};
            for (int i = 0; i < 5; i++) {
                csvWriter.writeRecord(data);
            }
            csvWriter.flush();
            TarArchiveEntry entry = new TarArchiveEntry(new File("/q/b/d.csv"));

            entry.setSize(baos.size());
            tos.putArchiveEntry(entry);
            baos.writeTo(tos);

//            tos.write(bytes);
//            file 2
            tos.closeArchiveEntry();
//
//            TarArchiveEntry entry1 = new TarArchiveEntry(new File("a/c.txt"));
//            entry1.setSize(6);
//            tos.putArchiveEntry(entry1);
//
//            tos.write(bytes1);
//            tos.closeArchiveEntry();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
