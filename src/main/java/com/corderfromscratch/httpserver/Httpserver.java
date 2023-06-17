package com.corderfromscratch.httpserver;

import com.corderfromscratch.httpserver.config.Configuration;
import com.corderfromscratch.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;

/**
 *
 * driver class for the http Server
 */
public class Httpserver {

    public static void main(String[] args){

        System.out.println("Server Starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using Port " + conf.getPort());
        System.out.println("Using WebRoot: " + conf.getWebroot());

        
        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = "<html><head><title>Simple Java HTTP Server</title></head><body>This page was my Simple Java HTTP Server</body></html>";

            final String CRLF = "\r\n"; // 13,10

            String response =
                    "HTTP/1.1 200 OK" + CRLF + // Status Line : HTTP VERSION RESPONSE_CORE RESPONSE_MESSAGE
                    "Content-Legth: " + html.getBytes().length + CRLF + // HEADER
                            CRLF +
                            html +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
