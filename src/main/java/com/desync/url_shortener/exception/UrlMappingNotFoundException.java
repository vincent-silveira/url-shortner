package com.desync.url_shortener.exception;

/**
 * Exception thrown when a requested URL mapping cannot be found.
 */
public class UrlMappingNotFoundException extends RuntimeException{

    /**
     * Constructs a new {@code UrlMappingNotFoundException} with the specified detail message.
     *
     * @param message the detail message describing the reason for the exception
     */
    public UrlMappingNotFoundException(String message) {
        super(message);
    }
}
