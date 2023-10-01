package io.dustin.domain.user.service

import io.dustin.domain.user.model.User
import io.dustin.domain.user.repository.UserRepository
import org.springframework.data.relational.core.sql.SqlIdentifier
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class WriteUserService(
    private val userRepository: UserRepository
) {

    fun create(user: User): Mono<User> {
        return userRepository.save(user)
    }

    fun update(user: User, assignments: MutableMap<SqlIdentifier, Any>): Mono<User> {
        return userRepository.updateUser(user, assignments)
    }

}