package com.springboot.bootdemo.base.webSocket;

import com.alibaba.fastjson.JSON;
import com.springboot.bootdemo.msg.MessageMsg;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/echo/{name}")
@Component
public class WebSocketTest {

    private final Logger logger = Logger.getLogger(WebSocketTest.class);

    private static ConcurrentHashMap<String,WebSocketTest> webSocketTestHashMap = new ConcurrentHashMap<>();

    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @OnOpen
    public void onOpen(Session session,@PathParam(value = "name") String name){
        logger.info("webSocket连接成功...."+name);
        this.session = session;

        webSocketTestHashMap.put(name,this);
    }

    @OnMessage
    public void onMessage(String message){
        logger.info("接收到了消息" + message);
//        if (message.indexOf("TOUSER") == 0){
        MessageMsg messageMsg = JSON.parseObject(message,MessageMsg.class);
        String sessionId = String.valueOf(messageMsg.getPlayerIndex());
        sendToSomeBody(sessionId,messageMsg.getMessage());
//        }
    }


    @OnClose
    public void onClose(){
        webSocketTestHashMap.remove(session.getId());
        logger.info("连接关闭....");
    }


    public void sendToSomeBody(String sessionId,String message){
        try {
//            session.getBasicRemote().sendText("服务端收到了。");
            WebSocketTest webSocketTest = webSocketTestHashMap.get(sessionId);
            if (webSocketTest != null) {
                webSocketTest.session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
