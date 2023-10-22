package io.dustin.api.router.mugi

import io.dustin.domain.mugi.service.ReadMugiService
import io.dustin.domain.mugi.service.WriteMugiService
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.net.URI

@Service
class WriteMugiHandler(
    private val read: ReadMugiService,
    private val write: WriteMugiService,
) {

    fun insert(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(CreateRecord::class.java)
            .flatMap {
                validate(it)
                write.create(it.toEntity())
            }
            .flatMap {
                ServerResponse.created(URI.create("/api/v1/records/${it.id}"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(it.toMono(), Record::class.java)
            }
    }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toLong()
        return request.bodyToMono(UpdateRecord::class.java)
            .flatMap {
                validate(it)
                read.recordByIdOrThrow(id)
                    .flatMap { entity ->
                        val (record, assignments) = it.createAssignments(entity)
                        write.update(record, assignments)
                    }.then(read.recordById(id))
            }
            .flatMap {
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(it.toMono(), Musician::class.java)
            }
    }

}