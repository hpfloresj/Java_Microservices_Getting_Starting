package com.hpfloresj.microservices.quizgame.exception;

public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287162L;

    public ApplicationException() {
        super();
    }

    public ApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(final String message) {
        super(message);
    }

    public ApplicationException(final Throwable cause) {
        super(cause);
    }
}
