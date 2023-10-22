package io.dustin.api.router.user

import io.dustin.api.router.user.model.CreateUser
import io.dustin.api.router.user.model.UpdateUser
import io.dustin.common.utils.validate
import io.dustin.domain.user.model.User
import io.dustin.domain.user.service.ReadUserService
import io.dustin.domain.user.service.WriteUserService
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.net.URI

@Service
class WriteUserHanlder(
    private val read: ReadUserService,
    private val write: WriteUserService,
) {

    fun insert(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(CreateUser::class.java)
            .flatMap {
                validate(it)
                write.create(it.toEntity())
            }
            .flatMap {
                ServerResponse.created(URI.create("/api/v1/musicians/${it.id}"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(it.toMono(), User::class.java)
            }
    }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toLong()
        return request.bodyToMono(UpdateUser::class.java)
            .flatMap {
                validate(it)
                read.userByIdOrThrow(id)
                    .flatMap { entity ->
                        val (musician, assignments) = it.createAssignments(entity)
                        write.update(musician, assignments)
                    }.then(read.userById(id))
            }
            .flatMap {
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(it.toMono(), User::class.java)
            }
    }

}