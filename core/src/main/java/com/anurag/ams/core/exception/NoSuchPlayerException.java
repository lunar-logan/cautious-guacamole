package com.anurag.ams.core.exception;

import com.anurag.ams.core.domain.Player;

/**
 * Created by IntelliJ IDEA on 10/28/18
 *
 * @author Anurag Gautam
 */
public class NoSuchPlayerException extends SystemException {
    private static final long serialVersionUID = 7775623615957934504L;

    public NoSuchPlayerException() {
    }

    public NoSuchPlayerException(String message) {
        super(message);
    }

    public NoSuchPlayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchPlayerException(Throwable cause) {
        super(cause);
    }

    public NoSuchPlayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
