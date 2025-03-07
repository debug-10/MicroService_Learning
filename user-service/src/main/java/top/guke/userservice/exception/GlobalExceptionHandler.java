package top.guke.userservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)  // 设置HTTP状态码为503
    public ResponseEntity<?> handleException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "用户服务正在维护中，请稍后。。。",
                null);
        return new ResponseEntity<>(errorDetails, HttpStatus.SERVICE_UNAVAILABLE);
    }
}

@Setter
@Getter
class ErrorDetails {
    // Getters and Setters
    private int code;
    private String message;
    private Object data;

    public ErrorDetails(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}