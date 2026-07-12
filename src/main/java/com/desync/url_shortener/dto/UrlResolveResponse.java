package com.desync.url_shortener.dto;

import lombok.Builder;

/**
 * Represents the response returned when resolving a shortened URL.
 *
 * @param originalUrl the original URL associated with the provided short code
 */
@Builder
public record UrlResolveResponse(
        String originalUrl
) { }
