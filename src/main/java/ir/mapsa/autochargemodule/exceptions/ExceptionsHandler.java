package ir.mapsa.autochargemodule.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice

public class ExceptionsHandler {

        @ExceptionHandler(RestException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<ExceptionResponse> handleException(Exception exception){
            ExceptionResponse exceptionResponse=new ExceptionResponse();
            exceptionResponse.setError(true);
            return ResponseEntity.badRequest().body(exceptionResponse);
        }

    }
class ExceptionResponse{

    private boolean error;
    private String message;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
