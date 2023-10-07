package io.dustin.api.usercase.user

import io.dustin.api.usercase.user.model.CreateUser
import io.dustin.api.usercase.user.model.UpdateUser
import io.dustin.common.exception.BadParameterException
import io.dustin.domain.user.model.User
import io.dustin.domain.user.service.ReadUserService
import io.dustin.domain.user.service.WriteUserService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class WriteUserUseCase(
    private val read: ReadUserService,
    private val write: WriteUserService,
) {

    fun insert(command: CreateUser): Mono<User> {
        val created = User(name = command.name, job = command.job)
        return write.create(created)
    }

    /**
     * flatMap을 사용하지 않는다면 이 결과는 Mono<Mono<Musician>>이라는 요상한 형식이 된다.
     */
    fun update(id: Long, command: UpdateUser): Mono<User> {
        return read.userByIdOrThrow(id).flatMap { user ->
            val (user, assignments) = command.createAssignments(user)
            write.update(user, assignments)
        }.onErrorResume {
            Mono.error(BadParameterException(it.message))
        }
            .then(read.userById(id))
    }

}