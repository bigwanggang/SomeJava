package com.gustavo.telnet;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gustaov on 2019/8/4.
 */
public class ServerManager {
    private static ServerManager serverManager = new ServerManager();
    private static AtomicInteger taskid = new AtomicInteger();
    private static ConcurrentHashMap<String, TaskInfo> taskInfo = new ConcurrentHashMap<String, TaskInfo>();

    private ServerManager() {

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
}
