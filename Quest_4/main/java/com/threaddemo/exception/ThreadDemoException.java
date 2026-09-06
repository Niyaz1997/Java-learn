package com.threaddemo.exception;

public class ThreadDemoException extends RuntimeException {

    public ThreadDemoException(String message) {
        super(message);
    }

    public ThreadDemoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThreadDemoException(Throwable cause) {
        super(cause);
    }
}