package com.corderfromscratch.httpserver;

import com.corderfromscratch.httpserver.config.Configuration;
import com.corderfromscratch.httpserver.config.ConfigurationManager;
import com.corderfromscratch.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.slf4j.LoggerFactory.*;

/**
 *
 * driver class for the http Server
 */
public class Httpserver {

    private final static Logger LOGGER = getLogger(Httpserver.class);
    public static void main(String[] args){

        LOGGER.info("Server Starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using Port " + conf.getPort());
        LOGGER.info("Using WebRoot: " + conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread  = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO handle Later.
        }


    }

}
