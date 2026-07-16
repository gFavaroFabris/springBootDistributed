package it.spring.example.cashcard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Configurazione JPA Auditing per @CreatedDate e @LastModifiedDate
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
}
