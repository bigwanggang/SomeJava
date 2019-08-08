package com.gustavo.telnet;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * CHECK UPDATE RESULT:TASKNO=1
 * Created by gustaov on 2019/8/3.
 */
public class Utils {
    public static String getResult(String command) {
        if (command.equals("CHECK DATA VERSION")) {
            return "ACK CHECK DATA VERSION:VERSION=\"V1.2.3\",ERRCODE=0,SOME=\"HEHE\";";
        } else if (command.equals("CHECK SERVER VERSION")) {
            return "ACK CHECK SERVER VERSION:VERSION=\"V1.2.3\",ERRCODE=0,SOME=\"HEHE\";";
        } else if (command.startsWith("DATA UPDATE:")) {
            command = StringUtils.substringAfter(command, "DATA UPDATE:");

//            command = "PATH=\"\\a\\b\",SOURCEVERSION=\"V1.2.3\", TARGETVERSION=\"V1.2.4\"";
            return new DataUpdateHandler(command).getResult();
        } else if (command.startsWith("CHECK UPDATE RESULT:")) {
            command = StringUtils.substringAfter(command, "CHECK UPDATE RESULT:");
            String taskNo = StringUtils.substringAfter(command, "TASKNO=");
            TaskInfo task = ServerManager.getInstance().getTask(taskNo);
            if (task == null) {
                return "taskNo: " + taskNo + " not found";
            } else {
                return MessageFormat.format("ACK CHECK UPDATE RESULT:ERRCODE=0,TASKNO={0},PROGESS={1}%,STATUS={2};", task.getTaskNo(),
                        task.getProgress(), task.getInfo());
            }
        }
        return "command not found: " + command;
    }

    public static Map<String, String> getMapInfo(String command) {
        Map<String, String> map = new HashMap<String, String>();
        String[] infos = StringUtils.split(command, ",");
        for (String info : infos) {
            String key = StringUtils.substringBefore(info, "=");
            String value = StringUtils.substringAfter(info, "=");
            if (value.startsWith("\"")) {
                value = StringUtils.substringAfter(value, "\"");
            }
            if (value.endsWith("\"")) {
                value = StringUtils.substringBefore(value, "\"");
            }
            map.put(key, value);
        }
        return map;
    }
}
