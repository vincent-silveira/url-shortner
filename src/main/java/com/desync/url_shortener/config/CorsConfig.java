package com.desync.url_shortener.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures Cross-Origin Resource Sharing (CORS) settings
 * for the application.
 *
 * <p>Allows the configured frontend application to access
 * backend endpoints using the supported HTTP methods.</p>
 */
@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {

    private final AppProperties appProperties;

    /**
     * Configures CORS mappings for application endpoints.
     *
     * <p>Requests are allowed from the configured frontend URL
     * and may use the supported HTTP methods.</p>
     *
     * @param registry the CORS registry used to configure cross-origin mappings
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins(appProperties.getFrontEndUrl())
                .allowedMethods(HttpMethod.GET.name(),
                        HttpMethod.POST.name()
                )
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
