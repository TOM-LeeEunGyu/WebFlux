package io.dustin.domain.user.repository

import io.dustin.domain.user.model.User
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserRepository: ReactiveCrudRepository<User, Long>{
    override fun findById(id: Long): Mono<User>
    fun findAllBy(pageable: Pageable): Flux<User>
}