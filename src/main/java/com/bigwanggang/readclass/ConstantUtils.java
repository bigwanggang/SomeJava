package com.bigwanggang.readclass;


import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class constantUtils {

    public static Set<Integer> CONSTANT_VALUES = new HashSet<>(Arrays.asList(
            7, 9, 10, 11, 8, 3, 4, 5, 6, 12, 1, 15, 16, 18
    ));

    public static String get2byte_2byteInfo(InputStream inputStream) throws IOException {
        byte[] class_index = new byte[2];
        byte[] name_and_type_index = new byte[2];

        inputStream.read(class_index);
        inputStream.read(name_and_type_index);

        int a = getValue(class_index);
        int b = getValue(name_and_type_index);
        return "#" + a + ".#" + b;

    }

    public static int get2byteInfo(InputStream inputStream) throws IOException {
        byte[] info = new byte[2];
        inputStream.read(info);
        return getValue(info);
    }

    public static String get4byteInfo(InputStream inputStream) throws IOException {
        byte[] info = new byte[4];
        inputStream.read(info);
        return "#" + getValue(info);
    }

    public static long get4byte_4byteInfo(InputStream inputStream) throws IOException {
        byte[] info1 = new byte[4];
        byte[] info2 = new byte[4];
        inputStream.read(info1);
        inputStream.read(info2);
        int a = getValue(info1);
        int b = getValue(info2);
        return ((long) a << 32 ) + b;
    }

    public static String get1byte_2byteInfo(InputStream inputStream) throws IOException {
        byte[] info1 = new byte[1];
        byte[] info2 = new byte[2];
        inputStream.read(info1);
        inputStream.read(info2);
        return "#" + getValue(info1) + ".#" + getValue(info2);
    }


    public static int getValue(byte[] bytes) {
        int value = 0;
        int len = bytes.length;
        for (int i = 0; i < len; i++) {
            value <<= 8;
            value |= (bytes[i] & 0xff);
        }
        return value;
    }
}
