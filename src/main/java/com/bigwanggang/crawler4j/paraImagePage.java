package com.bigwanggang.crawler4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/**
 * Hello world!
 */
public class paraImagePage {
    public static void main(String[] args) throws IOException {
        String urlstr = "http://storage.slide.news.sina.com.cn/slidenews/1_t5000/2017_22/64237_1094872_696203.jpg";

        saveImage(urlstr, "e:/crawler/tmp/isjdfsd.jpg");

    }
    public static void saveImage(String urlStr, String path) throws IOException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP,
                new InetSocketAddress("proxy.zte.com.cn", 80));

        URL url = new URL(urlStr);

        URLConnection con = url.openConnection(proxy);
        InputStream is = con.getInputStream();
        byte[] bs = new byte[1024];
        int len;

        OutputStream os = new FileOutputStream(path);

        while((len = is.read(bs))!=-1)
            os.write(bs,0,len);

        os.close();
        is.close();
    }
}
