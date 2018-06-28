package examples.boot.simplechat.socket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import examples.boot.simplechat.amqp.FanoutSender;
import examples.boot.simplechat.security.LoginUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {
    @Autowired
    SessionManager sessionManager;

    @Autowired
    FanoutSender sender;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {

        String name = getUser(session);
        System.out.println("-------------------------------------------------------");
        System.out.println(name);
        System.out.println(message.getPayload());
        System.out.println("-------------------------------------------------------");


        TypeReference<HashMap<String,Object>> typeRef
                = new TypeReference<HashMap<String,Object>>() {};
        HashMap<String, String> map = objectMapper.readValue(message.getPayload(), typeRef);
        map.put("name", name); // login 정보를 어떻게 읽어와야할지 고민하자.

        String msg = objectMapper.writeValueAsString(map);

        sender.send(msg);
    }

    // HttpSessionHandshakeInterceptor 이 WebSocket 설정으로
    // 되어 있어야지만 접속한 사용자 정보를 읽어들일 수 있다.
    private String getUser(WebSocketSession session) {
        Map<String, Object> handshakeAttributes = session.getAttributes();
        SecurityContext context = (SecurityContext) handshakeAttributes
                .get("SPRING_SECURITY_CONTEXT");
        Authentication authentication = context.getAuthentication();
        LoginUserInfo principal = (LoginUserInfo) authentication.getPrincipal();

        if (principal != null) {
            String name = principal.getName();
            return name;
        }

        return null;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        InetSocketAddress clientAddress = session.getRemoteAddress();
        HttpHeaders handshakeHeaders = session.getHandshakeHeaders();

//        logger.info("Handshake header: Accept {}", handshakeHeaders.toString());
//        logger.info("Handshake header: User-Agent {}", handshakeHeaders.get("User-Agent").toString());
//        logger.info("Handshake header: Sec-WebSocket-Extensions {}", handshakeHeaders.get("Sec-WebSocket-Extensions").toString());
//        logger.info("Handshake header: Sec-WebSocket-Key {}", handshakeHeaders.get("Sec-WebSocket-Key").toString());
//        logger.info("Handshake header: Sec-WebSocket-Version {}", handshakeHeaders.get("Sec-WebSocket-Version").toString());
        sessionManager.addWebSocketSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);

        sessionManager.remove(session);

    }
}