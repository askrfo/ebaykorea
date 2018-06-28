package examples.boot.simplechat.amqp;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FanoutSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanout;


    public void send(String msg) {

        try {
            rabbitTemplate.convertAndSend(fanout.getName(), "", msg);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
