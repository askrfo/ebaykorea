package examples.boot.myboard.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyBean {
    @Value("${name}")
    private String name;

    @PostConstruct
    public void printName(){
        System.out.println("=================");
        System.out.println("name : " + name);
        System.out.println("=================");
    }
}
