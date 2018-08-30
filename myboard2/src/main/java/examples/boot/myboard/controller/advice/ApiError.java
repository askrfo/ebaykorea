package examples.boot.myboard.controller.advice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Setter
@Getter
public class ApiError {
    private HttpStatus status;
    private String message;


    public ApiError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
}
