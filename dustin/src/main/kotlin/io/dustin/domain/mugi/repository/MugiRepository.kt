package io.dustin.domain.mugi.repository

import io.dustin.domain.mugi.model.Mugi
import io.dustin.domain.mugi.repository.custom.CustomMugiRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.Param
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MugiRepository: R2dbcRepository<Mugi, Long>, CustomMugiRepository {
    override fun findById(id: Long): Mono<Mugi>
    fun findByUserId(id: Long, pageable: Pageable): Flux<Mugi>
    @Query("SELECT COUNT(id) FROM mugi WHERE user_id = :userId")
    fun countByUserId(@Param("userId") userId: Long): Mono<Long>

    @Query("""
            SELECT user.name AS userName,
                   user.job,
                   user.created_at AS mCreatedAt,
                   user.updated_at AS mUpdatedAt,
                   mugi.*
              FROM mugi
              INNER JOIN user
              ON mugi.user_id = user_id
        """)
    fun findMugis(): Flux<Mugi>

}