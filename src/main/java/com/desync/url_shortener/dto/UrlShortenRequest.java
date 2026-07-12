package com.desync.url_shortener.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 * Represents a request to shorten an original URL.
 *
 * @param originalUrl the original URL to be shortened
 */
public record UrlShortenRequest(
        @NotBlank(message = "Original URL is required")
        @URL(message = "Invalid URL format")
        String originalUrl
) { }
