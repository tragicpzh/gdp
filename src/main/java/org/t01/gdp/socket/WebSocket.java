package org.t01.gdp.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.t01.gdp.service.MyLogService;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;


@Component
@ServerEndpoint("/administrator/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
//        MyLogService.info("【websocket消息】有新的连接, 总数:%s", String.valueOf(webSocketSet.size()));
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
//        MyLogService.info("【websocket消息】连接断开, 总数:%s", String.valueOf(webSocketSet.size()));
    }

    @OnMessage
    public void onMessage(String message) {
        MyLogService.info("【websocket消息】收到客户端发来的消息:%s", message);
    }

    public static void sendMessage(String message) {
        for (WebSocket webSocket: webSocketSet) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}