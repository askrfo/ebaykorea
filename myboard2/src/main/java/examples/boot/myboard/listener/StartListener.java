package examples.boot.myboard.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class StartListener
        implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(
            ApplicationStartedEvent applicationStartedEvent) {
        System.out.println("----------------------");
        System.out.println(" Application Start !!! ");
        System.out.println("----------------------");
    }
}
