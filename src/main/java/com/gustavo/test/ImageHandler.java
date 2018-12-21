package com.gustavo.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ImageHandler {
    public static void main(String[] args) throws IOException {
        Set<RGB> rgbs = new HashSet<>();
        System.out.println();
        File image = new File("D:\\123.jpg");
        BufferedImage bi = ImageIO.read(new FileInputStream(image));
        System.out.println("height: " + bi.getHeight());
        System.out.println("width: " + bi.getWidth());

        BufferedImage newImage = new BufferedImage(150, 169, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();

        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                int pixel = bi.getRGB(j, i);
                RGB rgb = getRgb(pixel);
                rgbs.add(rgb);
                graphics.setColor(new Color(rgb.red, rgb.green, rgb.blue));
                graphics.fillRect(j,i,1,1);
            }
            System.out.println();
        }

        System.out.println(rgbs);
        System.out.println(rgbs.size());

        ImageIO.write(newImage, "jpg", new File("d:\\45.jpg"));
    }

    public static RGB getRgb(int pixel) {
        int blue = getPixel(pixel & 0xff);
        int green = getPixel((pixel >> 8) & 0xff);
        int red = getPixel((pixel >> 16) & 0xff);

        return new RGB(red, green, blue);
    }

    public static int getPixel(int pixel) {
        int value = 0;
        if (pixel < 64) {
            value = 0;
        } else if (pixel >= 64 && pixel <= 192) {
            value = 128;
        } else {
            value = 255;
        }
        return value;
    }
}
