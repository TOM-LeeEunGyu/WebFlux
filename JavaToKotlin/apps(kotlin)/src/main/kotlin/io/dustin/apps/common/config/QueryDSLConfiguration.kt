package io.dustin.apps.common.config

import com.querydsl.jpa.impl.JPAQueryFactory

@Configuration
class QueryDSLConfiguration {
    @Bean
    fun jpaQueryFactory(em: EntityManager?): JPAQueryFactory? {
        return JPAQueryFactory(em)
    }
}
