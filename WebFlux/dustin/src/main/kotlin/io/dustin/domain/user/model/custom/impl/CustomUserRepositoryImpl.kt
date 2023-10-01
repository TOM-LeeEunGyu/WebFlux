package io.dustin.domain.user.model.custom.impl

import io.dustin.domain.user.model.User
import io.dustin.domain.user.model.custom.CustomUserRepository
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.Query.query
import org.springframework.data.relational.core.query.Update
import org.springframework.data.relational.core.sql.SqlIdentifier
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class CustomUserRepositoryImpl(
    private val query: R2dbcEntityTemplate,
): CustomUserRepository {

    /** 이 코드는 주어진 사용자(user)의 ID를 기반으로 데이터베이스에서 업데이트 작업을 수행하고, assignments 맵에 정의된 업데이트 내용을 적용한 후 업데이트된 사용자 객체를 비동기적으로 반환합니다?(완벽히 이해 못함). */
    override fun updateUser(user: User, assignments: MutableMap<SqlIdentifier, Any>): Mono<User> {
        return query.update(User::class.java)
            .matching(query(where("id").`is`(user.id!!)))
            .apply(Update.from(assignments))
            .thenReturn(user)
    }

    override fun usersByQuery(match: Query): Flux<User> {
        return query.select(User::class.java)
            .matching(match)
            .all()
    }

    override fun totalCountByQuery(match: Query): Mono<Long> {
        return query.select(User::class.java)
            .matching(match)
            .count()
    }

}