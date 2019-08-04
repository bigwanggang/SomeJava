package com.gustavo.telnet;

import java.text.MessageFormat;
import java.util.Map;

/**
 * DATA UPDATE:PATH="a",SOURCEVERSION="V1",TARGETVERSION="v2"
 * Created by gustaov on 2019/8/4.
 */
public class DataUpdateHandler {
    private static String COMMAND_RESULT = "ACK DATA UPDATE:TASKNO={0}";
    private String command;
    private String result;

    public DataUpdateHandler(String command) {
        this.command = command;
        Map<String, String> map = Utils.getMapInfo(command);
        String path = map.get("PATH");
        String sourceVersion = map.get("SOURCEVERSION");
        String targetVersion = map.get("TARGETVERSION");
        TaskInfo task = new TaskInfo(sourceVersion, targetVersion, path);
        ServerManager.getInstance().addTask(task);
        result = MessageFormat.format(COMMAND_RESULT, task.getTaskNo());
    }

    public String getResult() {
        return result;
    }
}
