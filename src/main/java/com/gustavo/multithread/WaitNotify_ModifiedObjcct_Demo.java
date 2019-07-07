package com.gustavo.multithread;

import java.util.concurrent.TimeUnit;

/**
 * 验证，被锁的对象，是不变的，本栗验证如果对象改变了，还能正常运行吗
 */
public class WaitNotify_ModifiedObjcct_Demo {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Student s = new Student("james", "kobe");
        new Thread(new Consumer(s)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s.setTeacher("hehe");
        new Thread(new Producer(s)).start();
    }

    static class Producer implements Runnable {
        Student s;

        public Producer(Student s) {
            this.s = s;
        }

        @Override
        public void run() {
            synchronized (s) {
                s.notifyAll();
            }
        }
    }

    static class Consumer implements Runnable {

        Student s;

        public Consumer(Student s) {
            this.s = s;
        }

        @Override
        public void run() {
            synchronized (s) {
                System.out.println(Thread.currentThread().getName() + " get the lock time: " + System.currentTimeMillis());
                try {
                    s.wait();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " get the list value: " + System.currentTimeMillis());
            }
        }
    }

    static class Student {
        private String name;
        private String teacher;

        public Student(String name, String teacher) {
            this.name = name;
            this.teacher = teacher;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }
    }
}
