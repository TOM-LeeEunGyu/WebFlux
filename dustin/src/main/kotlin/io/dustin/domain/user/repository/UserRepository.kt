package io.dustin.domain.user.repository

import io.dustin.domain.user.model.User
import io.dustin.domain.user.repository.custom.CustomUserRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserRepository: ReactiveCrudRepository<User, Long>, CustomUserRepository {
    override fun findById(id: Long): Mono<User>
    fun findAllBy(pageable: Pageable): Flux<User>
}