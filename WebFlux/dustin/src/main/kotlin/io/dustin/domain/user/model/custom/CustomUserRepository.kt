package io.dustin.domain.user.model.custom

import io.dustin.domain.user.model.User
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.sql.SqlIdentifier
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface CustomUserRepository {

    fun updateMusician(user: User, assignments: MutableMap<SqlIdentifier, Any>): Mono<User>

    fun usersByQuery(match: Query): Flux<User>

    fun totalCountByQuery(match: Query): Mono<Long>
}