package tn.esprit.SLTS_server.presentation.Socket;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatroom")
public class ChatroomServerEndpoint {
    static Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void handleOpen(Session session) {
        chatroomUsers.add(session);
    }
    @OnMessage
    public void handleMessage(String message ,Session session) throws IOException {
        String username =(String) session.getUserProperties().get("username");
        if(username==null)
        {
            session.getUserProperties().put("username", message);
            session.getBasicRemote().sendText(buildJsonData("ERP","You are now connected as " +message));
        } else {
            Iterator<Session> iterator = chatroomUsers.iterator();
            while(iterator.hasNext()) iterator.next().getBasicRemote().sendText(buildJsonData(username, message));
        }
    }
    @OnClose
    public void handleClose(Session session) {
        chatroomUsers.remove(session);
    }
    
    private String buildJsonData(String username,String message){
      JsonObject jsonobject = Json.createObjectBuilder().add("message", username+" : "+message).build();
      StringWriter stringwriter = new StringWriter();
      try (JsonWriter jsonwriter = Json.createWriter(stringwriter)){
          jsonwriter.write(jsonobject);
          
      }
      return stringwriter.toString();
    }
}
