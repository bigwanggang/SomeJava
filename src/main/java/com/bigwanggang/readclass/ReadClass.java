package com.bigwanggang.readclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadClass {
    public static void main(String[] args) throws IOException {
        String clazzFile = "D:\\workspace\\PlanDataRequest.class";

        InputStream is = new FileInputStream(clazzFile);

        byte[] byte1 = new byte[1];
        byte[] byte2 = new byte[2];
        byte[] byte4 = new byte[4];

        is.read(byte4);

        System.out.println("magic： " + constantUtils.getValue(byte4));

        is.read(byte2);
        System.out.println(constantUtils.getValue(byte2));

        is.read(byte2);
        System.out.println(constantUtils.getValue(byte2));

        is.read(byte2);
        System.out.println("Constant pool size: " + constantUtils.getValue(byte2));


        is.read(byte1);
        int type = constantUtils.getValue(byte1);
        int num =1;
        while (constantUtils.CONSTANT_VALUES.contains(type)) {
            System.out.print("#" + num++ + "\t\t");
            switch (type) {
                case 7:     //class
                    System.out.println("#" + constantUtils.get2byteInfo(is));
                    break;
                case 9:     //field
                    System.out.println(constantUtils.get2byte_2byteInfo(is));
                    break;
                case 10:    //method
                    System.out.println(constantUtils.get2byte_2byteInfo(is));
                    break;
                case 11:    //interface method
                    System.out.println(constantUtils.get2byte_2byteInfo(is));
                    break;
                case 8:     //string
                    System.out.println(constantUtils.get2byteInfo(is));
                    break;
                case 3:     //integer
                    System.out.println(constantUtils.get4byteInfo(is));
                    break;
                case 4:     //float
                    System.out.println(constantUtils.get4byteInfo(is));
                    break;
                case 5:     //long
                    System.out.println(constantUtils.get4byte_4byteInfo(is));
                    num++;
                    break;
                case 6:     //double
                    System.out.println(constantUtils.get4byte_4byteInfo(is));
                    num++;
                    break;
                case 12:    //name and type
                    System.out.println(constantUtils.get2byte_2byteInfo(is));
                    break;
                case 1:     // utf8
                    int length = constantUtils.get2byteInfo(is);
                    byte[] bytes = new byte[length];
                    is.read(bytes);
                    System.out.println(new String(bytes));
                    break;
                case 15:    //method handle
                    System.out.println(constantUtils.get1byte_2byteInfo(is));
                    break;
                case 16:    //method type
                    System.out.println(constantUtils.get2byteInfo(is));
                    break;
                case 18:    //invoke dynamic
                    System.out.println(constantUtils.get2byte_2byteInfo(is));
                    break;
                default:
                    break;
            }
            is.read(byte1);
            type = constantUtils.getValue(byte1);
        }
        System.out.println(type);
        is.read(byte1);
        type = constantUtils.getValue(byte1);
        System.out.println(type);
    }

}
