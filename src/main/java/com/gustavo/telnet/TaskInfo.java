package com.gustavo.telnet;

import lombok.Data;

/**
 * Created by gustaov on 2019/8/4.
 */
@Data
public class TaskInfo {
    private String taskNo;
    private int progress;
    private String info;
    private String sourceVersion;
    private String targetVersion;
    private String path;

    public TaskInfo(String sourceVersion, String targetVersion, String path) {
        this.sourceVersion = sourceVersion;
        this.targetVersion = targetVersion;
        this.path = path;
        this.progress = 0;
        this.info = "executing";
    }
}
