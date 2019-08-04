package com.gustavo.telnet;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gustaov on 2019/8/4.
 */
public class ServerManager {
    private static ServerManager serverManager = new ServerManager();
    private static AtomicInteger taskid = new AtomicInteger();
    private static ConcurrentHashMap<String, TaskInfo> taskInfo = new ConcurrentHashMap<String, TaskInfo>();

    private ServerManager() {
        Thread taskUpdateThread = new TaskUpdateThread();
        taskUpdateThread.setDaemon(true);
        taskUpdateThread.start();
    }

    public static ServerManager getInstance() {
        return serverManager;
    }

    public TaskInfo getTask(String taskId) {
        return taskInfo.get(taskId);
    }

    public void addTask(TaskInfo task) {
        String taskId = String.valueOf(taskid.incrementAndGet());
        task.setTaskNo(taskId);
        taskInfo.put(task.getTaskNo(), task);
    }

    private class TaskUpdateThread extends Thread {
        private Random random = new Random();

        @Override
        public void run() {
            while (true) {
                Iterator<Map.Entry<String, TaskInfo>> entries = taskInfo.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, TaskInfo> entry = entries.next();
                    TaskInfo t = entry.getValue();
                    int progress = t.getProgress();
                    System.out.println("taskInfo: " + t);
                    if (progress < 100) {
                        progress += random.nextInt(10);
                        System.out.println(progress);
                        if (progress >= 100) {
                            t.setProgress(100);
                            t.setInfo("success");
                        } else {
                            t.setProgress(progress);
                        }
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
