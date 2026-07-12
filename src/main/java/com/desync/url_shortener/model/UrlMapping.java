package com.desync.url_shortener.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entity representing a shortened URL mapping.
 * <p>
 * Each record stores the original URL provided by the user along with
 * its generated unique short code. The creation timestamp is automatically
 * populated when the entity is persisted.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "url_mappings")
public class UrlMapping {

    /**
     * Unique identifier for the URL mapping.
     * <p>
     * Generated using a database sequence.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_seq")
    @SequenceGenerator(
            name = "url_seq",
            sequenceName = "url_sequence",
            allocationSize = 50)
    private Long id;

    /**
     * The original URL submitted by the user.
     */
    @Column(name = "original_url", nullable = false, length = 2048)
    private String originalUrl;

    /**
     * Unique short code used to access the original URL.
     */
    @Column(name = "short_code", nullable = false, unique = true)
    private String shortCode;

    /**
     * Timestamp indicating when the URL mapping was created.
     * <p>
     * Automatically populated by Hibernate during persistence.
     */
    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

}