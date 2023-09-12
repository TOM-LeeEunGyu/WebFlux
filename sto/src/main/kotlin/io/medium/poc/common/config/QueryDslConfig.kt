package io.medium.poc.common.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * queryDsl configuration
 *
 * created by basquiat
 */
@Configuration
class QuerydslConfig(
    private val em: EntityManager,
) {

    @Bean
    fun querydsl(): JPAQueryFactory = JPAQueryFactory(em)

}
