package com.desync.url_shortener.repository;

import com.desync.url_shortener.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {

    /**
     * Finds a URL mapping by its short code.
     *
     * @param shortCode the short code associated with the URL mapping
     * @return an {@link Optional} containing the URL mapping if found,
     *         or an empty {@link Optional} if no mapping exists
     */
    Optional<UrlMapping> findByShortCode(String shortCode);

}
