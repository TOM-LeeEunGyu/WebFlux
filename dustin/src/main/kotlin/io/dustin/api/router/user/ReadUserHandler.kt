package io.dustin.api.router.user


import io.dustin.common.builder.createQuery
import io.dustin.common.model.request.QueryPage
import io.dustin.common.utils.searchMatrixVariable
import io.dustin.domain.user.model.User
import io.dustin.domain.user.service.ReadUserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
class ReadUserHandler(
    private val read: ReadUserService,
) {
    fun userById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toLong()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(read.userByIdOrThrow(id, "id [$id]로 조회되는 유저가 없습니다."), User::class.java)
    }

    fun userByQuery(request: ServerRequest): Mono<ServerResponse> {
        val queryPage = QueryPage.fromServerResponse(request)
        val matrixVariables = searchMatrixVariable(request)
        val match = createQuery(matrixVariables)
        val page = read.usersByQuery(queryPage.pagination(match))
            .collectList()
            .zipWith(read.totalCountByQuery(queryPage.pagination(match)))
            .map { tuple -> PageImpl(tuple.t1, queryPage.fromPageable(), tuple.t2) }
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(page, Page::class.java)
    }

}