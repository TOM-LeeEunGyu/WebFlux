package io.dustin.domain.mugi.repository

import io.dustin.domain.mugi.repository.custom.CustomMugiRepository
import io.dustin.domain.user.model.User
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MugiRepository: R2dbcRepository<User, Long>, CustomMugiRepository {
    override fun findById(id: Long): Mono<User>
    fun findByMusicianId(id: Long, pageable: Pageable): Flux<User>
    @Query("SELECT COUNT(id) FROM record WHERE musician_id = :musicianId")
    fun countByMusicianId(@Param("musicianId") musicianId: Long): Mono<Long>

    @Query("""
            SELECT user.name AS musicianName,
                   user.genre,
                   user.created_at AS mCreatedAt,
                   user.updated_at AS mUpdatedAt,
                   mugi.*
              FROM mugi
              INNER JOIN user
              ON mugi.user_id = user_id
        """)
    fun findRecords(): Flux<Record>

}