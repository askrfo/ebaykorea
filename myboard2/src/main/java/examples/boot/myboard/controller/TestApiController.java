package examples.boot.myboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8080/api/test 라고 요청하면 로그인화면으로 redirect되어야 한다.
@RestController
@RequestMapping("/api/test")
public class TestApiController {
    @GetMapping
    public String test(){
        return "test";
    }
}
