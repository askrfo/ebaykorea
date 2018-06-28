package examples.boot.simplechat.amqp;

import examples.boot.simplechat.socket.SessionManager;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

public class FanoutReceiver {

    @Autowired
    SessionManager sessionManager;

    private long index = 0;

    //    @RabbitListener(queues = "#{fanoutQueue.name}")
    public void receiveMessage(String message) {
        System.out.println("Received <<<<<<<<<<<<<<< pub/sub - fanout : " + message + ">>>>>>>>>>>>>>>>>>>>>>>>");

        TextMessage textMessage = new TextMessage(message);

        sessionManager.getWebSocketSessions().stream().forEach(s -> {
            try {
                s.sendMessage(textMessage);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }

}