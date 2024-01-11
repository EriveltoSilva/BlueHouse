package com.bluehouse.bluehouse.http;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResponseBody {
        private HttpStatus status;
    private String mensagem;
    private Object data;
    private LocalDateTime timestamp;
}
