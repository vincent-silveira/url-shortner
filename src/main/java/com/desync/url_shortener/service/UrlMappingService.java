package com.desync.url_shortener.service;

import com.desync.url_shortener.exception.UrlMappingNotFoundException;
import com.desync.url_shortener.model.UrlMapping;
import com.desync.url_shortener.repository.UrlMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service responsible for managing URL mappings.
 *
 * <p>Provides operations for creating shortened URL mappings
 * and retrieving the original URL associated with a short code.</p>
 */
@Service
@RequiredArgsConstructor
public class UrlMappingService {

    private static final String BASE62_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final UrlMappingRepository urlMappingRepository;

    /**
     * Generates a Base62-encoded short code from the specified number.
     *
     * @param number the numeric value to encode
     * @return the Base62 representation of the specified number
     */
    private String generateShortCode(long number){
        if (number == 0) {
            return String.valueOf(BASE62_CHARACTERS.charAt(0));
        }

        StringBuilder sb = new StringBuilder();
        while(number > 0){
            int remainder = (int) (number % 62);
            sb.append(BASE62_CHARACTERS.charAt(remainder));
            number /= 62;
        }

        return sb.reverse().toString();
    }

    /**
     * Creates a new URL mapping for the specified original URL.
     *
     * <p>The mapping is initially persisted with a temporary short code
     * to obtain its generated identifier. The identifier is then encoded
     * using Base62 and assigned as the final short code.</p>
     *
     * @param originalUrl the original URL to shorten
     * @return the created URL mapping containing the generated short code
     */
    @Transactional(readOnly = false)
    public UrlMapping createUrlMapping(String originalUrl){

        UrlMapping urlMapping = UrlMapping.builder()
                .originalUrl(originalUrl)
                .shortCode("PENDING"+System.nanoTime())
                .build();

        urlMapping = urlMappingRepository.save(urlMapping);

        String shortCode = generateShortCode(urlMapping.getId());

        urlMapping.setShortCode(shortCode);

        return urlMapping;
    }

    /**
     * Retrieves a URL mapping by its short code.
     *
     * @param shortCode the short code associated with the URL mapping
     * @return the URL mapping associated with the specified short code
     * @throws UrlMappingNotFoundException if no mapping exists for the specified short code
     */
    @Transactional(readOnly = true)
    public UrlMapping getUrlMappingByShortCode(String shortCode) {
        return urlMappingRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlMappingNotFoundException("Short URL mapping not found for code: " + shortCode));

    }
}
