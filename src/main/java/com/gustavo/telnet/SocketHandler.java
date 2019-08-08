package com.gustavo.telnet;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

import static com.gustavo.telnet.Utils.getResult;

/**
 * Created by gustaov on 2019/8/3.
 */
@Slf4j
public class SocketHandler implements Runnable {
    private Socket socket;
    private BufferedReader br = null;

    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            br = new BufferedReader(new InputStreamReader(in, "utf-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.write("welcome to to telnet server\r\n".getBytes());
            out.write("username:".getBytes());
            String username = readOneLine();
            out.write("password:".getBytes());
            String password = readOneLine();
            out.write("$>".getBytes());
            System.out.println("username: " + username + " login");
            String command = null;
            while (!"EXIT".equals(command)) {
                command = readOneLine();
                command = command.trim();
                log.info("receive command: " + command);
                String result = getResult(command);
                out.write(result.getBytes());
                out.write("\r\n$>".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String readOneLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
