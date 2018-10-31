package com.anurag.ams.core.exception;

/**
 * Created by IntelliJ IDEA on 10/28/18
 *
 * @author Anurag Gautam
 */
public class NoSuchStatException extends SystemException {
    private static final long serialVersionUID = -7652885844428097329L;

    public NoSuchStatException() {
    }

    public NoSuchStatException(String message) {
        super(message);
    }

    public NoSuchStatException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchStatException(Throwable cause) {
        super(cause);
    }

    public NoSuchStatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
