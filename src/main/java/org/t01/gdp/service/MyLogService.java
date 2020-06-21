package org.t01.gdp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.t01.gdp.socket.WebSocket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLogService {
    private static final Logger LOG = LoggerFactory.getLogger(MyLogService.class);

    public static void info(String message){
        WebSocket.sendMessage(new Date().toString() + ": INFO " + message);
        LOG.info(message);
    }

    public static void info(String format, String message){
        String formattedMessage = String.format(format,message);
        WebSocket.sendMessage(new Date().toString() + ": INFO " + formattedMessage);
        LOG.info(formattedMessage);
    }

    public static void warn(String message){
        WebSocket.sendMessage(new Date().toString() + ": WARN " + message);
        LOG.warn(message);
    }

    public static void warn(String format, String message){
        String formattedMessage = String.format(format,message);
        WebSocket.sendMessage(new Date().toString() + ": WARN " + formattedMessage);
        LOG.warn(formattedMessage);
    }
}
