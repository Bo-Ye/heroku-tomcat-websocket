package com.boye.websocket;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/message-endpoint")
public class MessageEndpoint {

    private Timer timer;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open session " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, final Session session) {
        System.out.println("Session " + session.getId() + " message: " + message);
        if ("GET".equals(message)) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        String msg = "Message " + UUID.randomUUID();
                        System.out.println(msg);
                        session.getBasicRemote().sendText(msg);
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }, 0, 1000);
        } else if ("STOP".equals(message)) {
            timer.cancel();
        }
    }

    @OnClose
    public void onClose(Session session) {
        timer.cancel();
        System.out.println("Session " + session.getId() + " is closed.");
    }
}
