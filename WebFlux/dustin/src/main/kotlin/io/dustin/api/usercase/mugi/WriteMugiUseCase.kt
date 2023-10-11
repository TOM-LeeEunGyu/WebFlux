package io.dustin.api.usercase.mugi

import io.dustin.api.usercase.mugi.model.CreateMugi
import io.dustin.api.usercase.mugi.model.UpdateMugi
import io.dustin.common.exception.BadParameterException
import io.dustin.domain.mugi.model.Mugi
import io.dustin.domain.mugi.service.ReadMugiService
import io.dustin.domain.mugi.service.WriteMugiService
import io.dustin.domain.user.service.ReadUserService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class WriteMugiUseCase(
    private val readUser: ReadUserService,
    private val read: ReadMugiService,
    private val write: WriteMugiService,
) {

    fun insert(command: CreateMugi): Mono<Mugi> {
        val created = Mugi(
            userId = command.userId,
            name = command.title,
            label = command.label,
            releasedType = command.releasedType,
            releasedYear = command.releasedYear,
            format = command.format,
        )
        val user = readUser.userByIdOrThrow(command.userId, "해당 무기를 소유한 유저 정보가 조회되지 않습니다. 유저 아이디를 확인하세요.")
        return user.flatMap {
            write.create(created)
        }
    }

    fun update(id: Long, command: UpdateMugi): Mono<Mugi> {
        return read.mugiByIdOrThrow(id).flatMap { mugi ->
            val (mugi, assignments) = command.createAssignments(mugi)
            write.update(mugi, assignments)
        }.onErrorResume {
            Mono.error(BadParameterException(it.message))
        }
            .then(read.mugiById(id))
    }

}