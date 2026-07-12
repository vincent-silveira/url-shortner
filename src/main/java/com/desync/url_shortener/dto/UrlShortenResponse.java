package com.desync.url_shortener.dto;

import lombok.Builder;

/**
 * Represents the response returned after shortening a URL.
 *
 * @param shortUrl    the complete shortened URL constructed using the
 *                    configured frontend URL
 * @param originalUrl the original URL associated with the shortened URL
 */
@Builder
public record UrlShortenResponse (
        String shortUrl,
        String originalUrl
) { }
