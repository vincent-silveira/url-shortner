package com.desync.url_shortener.controller;

import com.desync.url_shortener.config.AppProperties;
import com.desync.url_shortener.dto.UrlResolveResponse;
import com.desync.url_shortener.dto.UrlShortenRequest;
import com.desync.url_shortener.dto.UrlShortenResponse;
import com.desync.url_shortener.model.UrlMapping;
import com.desync.url_shortener.service.UrlMappingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * REST controller responsible for handling URL shortening
 * and URL resolution operations.
 *
 * <p>Provides endpoints for creating shortened URL mappings
 * and resolving short codes to their associated original URLs.</p>
 */
@RestController
@RequestMapping("/api/v1/urls")
@RequiredArgsConstructor
public class UrlMappingController {

    private final UrlMappingService urlMappingService;
    private final AppProperties appProperties;

    /**
     * Creates a shortened URL mapping for the provided original URL.
     *
     * <p>The generated short code is combined with the configured
     * frontend URL to create the complete shortened URL.</p>
     *
     * @param request the request containing the original URL to shorten
     * @return a response containing the original URL and generated short URL
     */
    @PostMapping(value = "/shorten")
    public ResponseEntity<UrlShortenResponse> createUrlMapping(
            @Valid
            @RequestBody
            UrlShortenRequest request
    ){
        String originalUrl = request.originalUrl();
        UrlMapping urlMapping = urlMappingService.createUrlMapping(originalUrl);

        String fullShortUrl = appProperties.getFrontEndUrl() + "/r/"
                + urlMapping.getShortCode();


        UrlShortenResponse response = UrlShortenResponse.builder()
                .originalUrl(originalUrl)
                .shortUrl(fullShortUrl)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    /**
     * Resolves a short code to its associated original URL.
     *
     * @param shortCode the short code identifying the URL mapping
     * @return a response containing the original URL
     */
    @GetMapping(value = "/resolve/{shortCode}")
    public ResponseEntity<UrlResolveResponse> resolveUrl(@PathVariable String shortCode){

        UrlMapping urlMapping = urlMappingService.getUrlMappingByShortCode(shortCode);

        UrlResolveResponse response = UrlResolveResponse.builder()
                .originalUrl(urlMapping.getOriginalUrl())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
