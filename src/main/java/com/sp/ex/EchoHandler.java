package com.sp.ex;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@RequestMapping("/echo")
public class EchoHandler extends TextWebSocketHandler{
    
    //������ ��� �����Ѵ�.
    //��� 1 :  1:1 ä��
	//private Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>();
    
    //��� 2 : ��ü ä��
    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
    
    private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
    
    /**
     * Ŭ���̾�Ʈ ���� ���Ŀ� ����Ǵ� �޼ҵ�
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	System.out.println("�ƾƾ�");
        //���� ���� ���
    	//sessions.put(session.getId(), session);
        //List���� ���
        sessionList.add(session);
        //���ǰ��� �ҷ���
        //0��° �߰�ȣ�� session.getId()�� ������¶�
        logger.info("{} �����", session.getId()); 
        
        //Session���� ������ �����ͺ��̽����� �۾��� �ϸ� ä�� ���� ����� ���� ����Ʈ�� ������ �� �ִ�.//
        //System.out.println("ä�ù� ������: " + session.getPrincipal().getName());
        return;
    }
    
    /**
     * Ŭ���̾�Ʈ�� ������ ������ �޽����� �������� �� ����Ǵ� �޼ҵ�
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	System.out.println("�ƾƾ�2");
        //0��°�� session.getId() 1��°�� message.getPayload() ����
        logger.info("id = {}  �� ���� {} ����", session.getAttributes().get("userID").toString(), message.getPayload());
        //logger.info("{}�κ��� {}����", new String[]{session.getId(),message.getPayload()});
        
        //����� ��� Ŭ���̾�Ʈ���� �޽��� ���� : ����Ʈ ���
        //getPrincipal()�� �̿��ؼ� ���ǿ� �����ִ� ������ ������ �ҷ��´�.������ ������ User�� �̿��ѰͰ� �����ϴ�.//
        for(WebSocketSession sess : sessionList){
        	sess.sendMessage(new TextMessage(/*session.getPrincipal().getName()*/"|" + message.getPayload()));
        }
        
        // �� ���.
        /*Iterator<String> sessionIds = sessions.ketSet().iterator();
        String sessionId = "";
        while (sessionIds.hasNext()) {
            sessionId = sessionIds.next();
            sessions.get(sessionId).sendMessage(new TextMessage("echo:" + message.getPayload()));
            
        }*/
        
        //����Ǿ� �ִ� ��� Ŭ���̾�Ʈ�鿡�� �޽����� �����Ѵ�.
        //session.sendMessage(new TextMessage("echo:" + message.getPayload()));
        return;
    }
    
    /**
     * Ŭ���̾�Ʈ ������ ������ �� ����Ǵ� �޼ҵ�
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	System.out.println("�ƾƾ�3");
        //List ����
        sessionList.remove(session);
        
        //Map ����
        //sessions.remove(session.getId());
        logger.info("{} ���� ����.", session.getId());
        return;
       // System.out.println("ä�ù� ������: " + session.getPrincipal().getName());
    }
 
}