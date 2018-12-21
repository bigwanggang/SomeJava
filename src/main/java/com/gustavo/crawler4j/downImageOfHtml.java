package com.gustavo.crawler4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class downImageOfHtml {
    public static void main(String[] args) throws IOException {
        String html = "http://slide.news.sina.com.cn/x/slide_1_64237_159292.html";

        Proxy proxy = new Proxy(Proxy.Type.HTTP,
                new InetSocketAddress("proxy.zte.com.cn", 80));

        URL url = new URL(html);

        URLConnection con = url.openConnection(proxy);

        InputStream is = con.getInputStream();
        byte[] bs = new byte[1024];
        int len;

        StringBuffer sb = new StringBuffer();

        while ((len = is.read(bs)) != -1) {
            sb.append(new String(bs));
        }

        Pattern imagePattern = Pattern.compile("http://.*\\.(jpg|png)");
        Matcher matcher = imagePattern.matcher(sb.toString());
        while (matcher.find()) {
            String imageHtml = matcher.group();
            if (!imageHtml.startsWith("http://storage.slide"))
                continue;
            String imageName = imageHtml.substring(imageHtml.lastIndexOf("/") + 1);
            System.out.println(imageHtml);
            try {
                paraImagePage.saveImage(imageHtml, "e:/crawler/tmp/" + imageName);
                System.out.println("saved: " + imageHtml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        OutputStream os = new FileOutputStream("e:/crawler/tmp/1234.html");
//
//        while((len = is.read(bs))!=-1) {
//            os.write(bs,0,len);
//            System.out.println("save: " + new String(bs));
//        }
//
//        os.close();
//        is.close();

    }
}
