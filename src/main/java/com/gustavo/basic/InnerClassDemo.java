package com.gustavo.basic;

/**
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
