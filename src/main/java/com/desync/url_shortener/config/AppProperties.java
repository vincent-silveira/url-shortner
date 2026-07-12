package com.desync.url_shortener.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;


/**
 * Configuration properties for application-specific settings.
 *
 * <p>Binds properties prefixed with {@code app} from the application
 * configuration and validates the configured values at application startup.</p>
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Validated
@Getter
@Setter
public class AppProperties {

    /**
     * Base URL of the frontend application.
     */
    @NotBlank(message = "The app.frontend-url configuration property must not be blank")
    @URL(message = "The app.frontend-url configuration property must be a valid URL")
    private String frontEndUrl;
}
