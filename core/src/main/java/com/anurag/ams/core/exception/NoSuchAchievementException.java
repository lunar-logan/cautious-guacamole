package com.anurag.ams.core.exception;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public class NoSuchAchievementException extends SystemException {
    private static final long serialVersionUID = 7197993806240154516L;

    public NoSuchAchievementException() {
    }

    public NoSuchAchievementException(String message) {
        super(message);
    }

    public NoSuchAchievementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchAchievementException(Throwable cause) {
        super(cause);
    }

    public NoSuchAchievementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
