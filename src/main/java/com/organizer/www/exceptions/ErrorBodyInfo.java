package com.organizer.www.exceptions;

public class ErrorBodyInfo {
    private final String url;
    private final String message;

    public ErrorBodyInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return this.url;
    }

    public String getMessage() {
        return this.message;
    }
}