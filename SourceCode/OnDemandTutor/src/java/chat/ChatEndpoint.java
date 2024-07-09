/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Lam Le
 */
@ServerEndpoint("/chat")
public class ChatEndpoint {

    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Received message: " + message); // Kiểm tra xem tin nhắn đã nhận đúng chưa
        String username = (String) session.getUserProperties().get("username");

        if (username == null) {
            session.getUserProperties().put("username", message);
            session.getBasicRemote().sendText("System: you are connected as " + message);
        } else {
            // Check if the message is intended for a specific user
            if (message.startsWith("@")) {
                String[] parts = message.split(":", 2);
                if (parts.length == 2) {
                    String recipientUsername = parts[0].substring(1).trim();
                    String userMessage = parts[1].trim();

                    synchronized (clients) {
                        for (Session client : clients) {
                            String clientUsername = (String) client.getUserProperties().get("username");
                            if (clientUsername != null && clientUsername.equals(recipientUsername)) {
                                client.getBasicRemote().sendText(username + ": " + userMessage);
                                break;
                            }
                        }
                    }
                }
            } else {
                synchronized (clients) {
                    for (Session client : clients) {
                        if (!client.equals(session)) {
                            client.getBasicRemote().sendText(username + ": " + message);
                        }
                    }
                }
            }
        }
    }
}
