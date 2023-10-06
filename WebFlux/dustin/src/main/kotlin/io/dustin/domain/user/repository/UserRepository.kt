package io.dustin.domain.user.repository

import io.dustin.domain.user.model.User
import io.dustin.domain.user.model.custom.CustomUserRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserRepository: R2dbcRepository<User, Long>, CustomUserRepository {
    override fun findById(id: Long): Mono<User>
    fun findAllBy(pageable: Pageable): Flux<User>
}