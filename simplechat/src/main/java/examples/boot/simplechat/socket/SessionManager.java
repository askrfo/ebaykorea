package examples.boot.simplechat.socket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SessionManager {
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public SessionManager(){
        System.out.println("SessionManager() !!!!! ");
    }
    public void addWebSocketSession(WebSocketSession webSocketSession){
        sessions.add(webSocketSession);
    }

    public List<WebSocketSession> getWebSocketSessions(){
        return sessions;
    }

    public void remove(WebSocketSession webSocketSession){
        sessions.remove(webSocketSession);
    }
}
