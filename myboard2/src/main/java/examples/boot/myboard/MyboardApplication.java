package examples.boot.myboard;

import examples.boot.myboard.listener.StartListener;
import examples.boot.myboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyboardApplication {
    public static void main(String[] args) {
        SpringApplication application =
                new SpringApplication(MyboardApplication.class);
        application.addListeners(new StartListener());
        application.run(args);
    }

}
