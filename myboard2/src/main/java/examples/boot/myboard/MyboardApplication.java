package examples.boot.myboard;

import examples.boot.ServerInfo;
import examples.boot.myboard.listener.StartListener;
import examples.boot.myboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyboardApplication {
//    @Bean
//    public ServerInfo serverInfo(){
//        ServerInfo serverInfo = new ServerInfo();
//        serverInfo.setAddress("localhost");
//        serverInfo.setPort(9999);
//        return serverInfo;
//    }

    public static void main(String[] args) {
        SpringApplication application =
                new SpringApplication(MyboardApplication.class);
        application.addListeners(new StartListener());
        application.run(args);
    }

}
