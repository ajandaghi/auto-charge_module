package ir.mapsa.autochargemodule.exceptions;

import org.springframework.http.HttpStatusCode;

public class RestException extends ServiceException{
    public RestException(String errorCode) {
        super(errorCode);
    }

    public RestException(String message, String errorCode) {
        super(message, errorCode);
    }

    public RestException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public RestException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }

    public RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}
