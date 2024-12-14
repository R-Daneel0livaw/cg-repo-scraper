package com.olivaw.codegraph.scraper.model;

public class GitActionResult<T> {
    private T data;
    private boolean success;
    private String message;

    public GitActionResult(T data) {
        this.data = data;
    }

    public GitActionResult(T data, boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}