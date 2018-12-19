package com.bigwanggang.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class ImageDemo {

    public static void main(String[] args) {
        targetZoomOut("d:\\44.jpg");
    }

    //本来就没错呀
    public static void targetZoomOut(String sourcePath) { //将目标图片缩小成256*256并保存
        File file1 = new File(sourcePath); //用file1取得图片名字
        String name = file1.getName();

        try {
            BufferedImage input = ImageIO.read(file1);
            BufferedImage inputbig = new BufferedImage(256, 256, BufferedImage.TYPE_INT_BGR);
            Graphics2D g = (Graphics2D) inputbig.getGraphics();
            g.drawImage(input, 0, 0,256,256,null); //画图
            g.dispose();
            inputbig.flush();

            File file2 = new File("e:/"); //此目录保存缩小后的关键图
            if (file2.exists()) {
                System.out.println("多级目录已经存在不需要创建！！");
            } else {
                //如果要创建的多级目录不存在才需要创建。
                file2.mkdirs();
            }
            String fname = name.substring(0, name.lastIndexOf("."));//新名字
            ImageIO.write(inputbig, "jpg", new File("e:/" + fname + ".jpg")); //将其保存在C:/imageSort/targetPIC/下
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
