package examples.boot.simplechat.config;

import examples.boot.simplechat.amqp.FanoutReceiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    static final String fanoutExchaneName = "spring-boot-fanoutExchange";

    static final String fanoutQueueName = "spring-boot-fanout";

    @Bean
    Queue fanoutQueue() {
        return new Queue(fanoutQueueName, false);
//        return new AnonymousQueue();
    }

    // pub/sub
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange(fanoutExchaneName);
    }

    @Bean
    public Binding fanoutBinding(FanoutExchange fanout,
                                 Queue fanoutQueue) {
        return BindingBuilder.bind(fanoutQueue).to(fanout);
    }

    @Bean
    SimpleMessageListenerContainer fanoutContainer(ConnectionFactory connectionFactory,
                                                   MessageListenerAdapter fanoutListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(fanoutQueueName);
        container.setMessageListener(fanoutListenerAdapter);
        return container;
    }

    @Bean
    FanoutReceiver fanoutReceiver(){
        return new FanoutReceiver();
    }

    @Bean
    MessageListenerAdapter fanoutListenerAdapter(FanoutReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
