package com.desync.url_shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the URL Shortener Spring Boot application.
 *
 * <p>This class bootstraps the Spring Boot application by initializing the
 * Spring Application Context, performing component scanning, enabling
 * auto-configuration, and starting the embedded web server.</p>
 *
 * @author Vincent
 * @since 1.0
 */
@SpringBootApplication
public class UrlShortenerApplication {

    /**
     * Starts the URL Shortener application.
     *
     * @param args command-line arguments passed during application startup
     */
	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

}
