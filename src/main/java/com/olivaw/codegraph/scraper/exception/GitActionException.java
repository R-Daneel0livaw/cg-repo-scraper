package com.olivaw.codegraph.scraper.exception;

public class GitActionException extends RuntimeException {
    public GitActionException(String message) {
        super(message);
    }

    public GitActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
