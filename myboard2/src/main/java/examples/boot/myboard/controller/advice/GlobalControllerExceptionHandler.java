package examples.boot.myboard.controller.advice;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
@Order(2)
public class GlobalControllerExceptionHandler {

//    @ExceptionHandler(value = CookieTheftException.class)
//    public String handleCookieTheftException(CookieTheftException e){
//
//        SecurityContextHolder.getContext().setAuthentication(null);
//        return "redirect:/members/login";
//    }
    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e) {

        return "exceptions/exception";
    }
}