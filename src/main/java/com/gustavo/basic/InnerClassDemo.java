package com.gustavo.basic;

/**
 * 内部类和静态内部类，创建内部类对象首先要有外部对象，
 * 而创建静态内部类不需要有外部类对象
 * Created by gustaov on 2019/4/21.
 */
public class InnerClassDemo {
    class InnerClass{
        int i;
        public InnerClass(int i) {
            this.i = i;
        }
    }
    static class StaticInnerClass{
        int i;

        public StaticInnerClass(int i) {
            this.i = i;
        }
    }

    public static void main(String[] args) {
        InnerClassDemo outClass = new InnerClassDemo();
        InnerClassDemo.InnerClass innerClass = outClass.new InnerClass(1);
        InnerClassDemo.StaticInnerClass staticInnerClass = new InnerClassDemo.StaticInnerClass(1);
    }
}
