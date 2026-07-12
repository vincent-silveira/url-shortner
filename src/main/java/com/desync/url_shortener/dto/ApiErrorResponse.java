package com.desync.url_shortener.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Represents a standardized error response returned by the API.
 *
 * @param status           the HTTP status code associated with the error
 * @param error            the HTTP error description
 * @param message          the detailed error message
 * @param path             the request path where the error occurred
 * @param timestamp        the date and time when the error occurred
 * @param validationErrors validation errors mapped by field name and error message,
 *                         or {@code null} if the error is not validation-related
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiErrorResponse(
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp,
        Map<String, String> validationErrors
) {}