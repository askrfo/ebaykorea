package examples.boot.myboard.service;

import examples.boot.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyBean {
    @Value("${name}")
    private String name;

    private Logger logger =
            LoggerFactory.getLogger(MyBean.class);

    @Autowired
    ServerInfo serverInfo;

    @PostConstruct
    public void printName(){
        logger.info("=================");
        logger.info("name : {}" ,  name);
        logger.info(serverInfo.toString());
        logger.info("=================");
    }
}
