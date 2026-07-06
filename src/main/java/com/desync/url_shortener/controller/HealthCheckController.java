package com.desync.url_shortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that exposes application health endpoints.
 * <p>
 * This endpoint can be used to verify that the application is
 * running and able to process requests.
 * </p>
 */
@RestController
public class HealthCheckController {

    /**
     * Returns a simple health status indicating that the application is available.
     *
     * @return an HTTP 200 response containing {@code "OK"}
     */
    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
