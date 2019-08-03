package com.gustavo.telnet;

/**
 * Created by gustaov on 2019/8/3.
 */
public class Utils {
    public static String getResult(String command) {
        if (command.equals("CHECK DATA VERSION")) {
            return "ACK CHECK DATA VERSION:VERSION=\"V1.2.3\",ERRCODE=0,SOME=\"HEHE\";";
        }
        return "command not found: " + command;
    }
}
