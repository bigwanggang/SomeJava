package com.gustavo.copy;

/**
 * Student类覆盖Object的clone方法，就可以调用对象的clone方法，但是如果Student类没有实现Cloneable,
 * 调用clone方法是就会抛出CloneNotSupportedException异常
 */
public class ShallowCopyDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Student s1 = new Student("00001", "james");
        Address address = new Address("800200", "jianguo Rode", "001");
        s1.setAddress(address);
        Student s = (Student) s1.clone();

        s.setName("hehe");
        s.getAddress().setNumber("002");

        System.out.println(s);
        System.out.println(s1);
    }
}
