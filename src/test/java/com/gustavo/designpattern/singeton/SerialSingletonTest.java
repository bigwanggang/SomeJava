package com.gustavo.designpattern.singeton;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.*;

/**
 * Created by gustaov on 2019/9/13.
 */
public class SerialSingletonTest {
    //测试
    @Test
    public void getInstance() throws Exception {
        SerialSingleton serialSingleton = SerialSingleton.getInstance();
        FileOutputStream fos = new FileOutputStream("text.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(serialSingleton);
        oos.flush();
        oos.close();

        FileInputStream fis  =new FileInputStream("text.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SerialSingleton s1 = (SerialSingleton) ois.readObject();
        Assert.assertTrue(s1 == serialSingleton);
        ois.close();
    }

}