package com.gustavo.zip;

import com.csvreader.CsvReader;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class TarReceiver {
    public static void main(String[] args) {
        int port = 9997;
        System.out.println("listen port: " + port);
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket socket = null;
        while (true) {
            try {

                socket = server.accept();
                System.out.println("client connect: " + socket.getRemoteSocketAddress());
                InputStream inputStream = socket.getInputStream();
                TarArchiveInputStream tar =
                        new TarArchiveInputStream(inputStream);
                TarArchiveEntry entry;
                while ((entry = tar.getNextTarEntry()) != null) {
                    System.out.println(entry.getName());
                    System.out.println(entry.getFile());
                    File f = new File("d:\\" + entry.getName());
                    f.createNewFile();
                    FileOutputStream fos = new FileOutputStream(f);
                    byte[] bytes = new byte[2048];
                    int read =0;
                    while ((read = inputStream.read(bytes)) != -1){
                        fos.write(bytes, 0,read);
                    }
                    fos.flush();
                    fos.close();

//                    byte[] bytes = new byte[tar.available()];
//                    tar.read(bytes);
//                    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
//                    System.out.println(bais.available());
//                    CsvReader reader = new CsvReader(bais, ',', Charset.forName("GBK"));
//                    CsvReader reader = new CsvReader(new InputStreamReader(bais));
//                    String[] head = reader.getHeaders(); //获取表头
//
//                    System.out.println("head" + head);
//                    while (reader.readRecord()) {
//                        for (int i = 0; i < head.length; i++) {
//                            System.out.println(head[i] + ":" + reader.get(head[i]));
//                        }
//
//                    }
//                    reader.close();
//                    System.out.println(entry.getName());
//                    int a = tar.available();
//                    byte[] bytes = new byte[a];
//                    tar.read(bytes);
//                    System.out.println(new String(bytes));

                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }


}
