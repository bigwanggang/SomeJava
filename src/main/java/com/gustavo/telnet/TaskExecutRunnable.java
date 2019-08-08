package com.gustavo.telnet;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by gustaov on 2019/8/8.
 */
@Slf4j
public class TaskExecutRunnable implements Runnable {
    private static Random random = new Random();
    private TaskInfo taskInfo;

    public TaskExecutRunnable(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    @Override
    public void run() {
        taskInfo.setInfo("");
        boolean isFinished = false;
        while (!isFinished) {
            int progress = taskInfo.getProgress() + random.nextInt(5);
            if (progress >= 100) {
                progress = 100;
                isFinished = true;
            }
            taskInfo.setProgress(progress);
            log.info("task is executing: " + taskInfo);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
