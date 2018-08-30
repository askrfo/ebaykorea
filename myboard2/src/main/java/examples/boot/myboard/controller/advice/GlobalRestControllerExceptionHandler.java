package examples.boot.myboard.controller.advice;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(annotations = RestController.class)
@Order(1)
public class GlobalRestControllerExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ApiError handleConflict(RuntimeException e,
                                      WebRequest request) {
        ApiError apiError =
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                        e.getLocalizedMessage());
        return apiError;
    }

}