package com.gastavo.test;

import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;

public class Jdom2Demo {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\workspace\\projects\\SpringBootInitializr\\src\\main\\resources\\abc.xml");
        Document doc = loadXml(file.getAbsolutePath());
        Element element = doc.getRootElement().getChild("bcd");
        Element add = new Element("script");
        add.setText("this is test");
        element = element.addContent(add);
        writeToXml(doc, file.getAbsolutePath());
    }

    public static Document loadXml(String path) {
        try {
            SAXBuilder parser = new SAXBuilder();
            return parser.build(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeToXml(Document doc, String path) {
        Format format = Format.getCompactFormat();
        format.setLineSeparator("\r\n");
        // 读取encoding的值,如果读取失败,用UTF-8表示
        try {
            File file = new File(path);
            if (file.exists()) {
                BufferedReader readSubRack = null;
                try {
                    readSubRack = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
                    String tmp = null;
                    String[] tmpstr = null;
                    if ((tmp = readSubRack.readLine()) != null) {
                        tmpstr = tmp.split("encoding=\"");
                    }

                    if (null != tmpstr && tmpstr.length > 1) {
                        tmpstr = tmpstr[1].split("\"");
                        if (tmpstr.length > 1) {
                            format.setEncoding(tmpstr[0].trim());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.closeQuietly(readSubRack);
                }
            }
            format.setIndent("    ");
            FileOutputStream fileout = null;
            try {
                XMLOutputter output = new XMLOutputter(format); // 修改后保存

                fileout = new FileOutputStream(path);
                output.output(doc, fileout);

                fileout.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(fileout);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
