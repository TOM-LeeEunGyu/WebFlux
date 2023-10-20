package io.dustin.api.router.mugi

import io.dustin.common.builder.createNativeSortLimitClause
import io.dustin.common.builder.createNativeWhereClause
import io.dustin.common.model.request.QueryPage
import io.dustin.common.utils.searchMatrixVariable
import io.dustin.domain.mugi.service.ReadMugiService
import io.dustin.domain.user.service.ReadUserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
class ReadMugiHandler(
    private val read: ReadMugiService,
    private val readUser: ReadUserService,
) {

    fun mugiById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toLong()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(read.mugiByIdOrThrow(id, "id [$id]로 조회되는 무기가 없습니다."), Record::class.java)
    }

    fun mugiByUserId(request: ServerRequest): Mono<ServerResponse> {
        val userId = request.pathVariable("userId").toLong()
        val queryPage = QueryPage.fromServerResponse(request)
        val user = readUser.userByIdOrThrow(userId)
        val page = user.flatMapMany { entity ->
            read.mugiByUserId(userId, queryPage.fromPageable())
                .map {
                    it.user = entity
                    it
                }
        }
            .collectList()
            .zipWith(read.mugiCountByUser(userId))
            .map { tuple -> PageImpl(tuple.t1, queryPage.fromPageable(), tuple.t2) }
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(page, Page::class.java)
    }

    fun allMugis(request: ServerRequest): Mono<ServerResponse> {
        val queryPage = QueryPage.fromServerResponse(request)
        val matrixVariables = searchMatrixVariable(request)
        val prefix = "mugi"
        val clazz = Record::class
        val whereClause = createNativeWhereClause(prefix, clazz, matrixVariables)
        val (orderSql, limitSql) = createNativeSortLimitClause(prefix, clazz, queryPage)
        val flux =  read.allMugis(whereClause = whereClause, orderClause = orderSql, limitClause = limitSql)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(flux, Record::class.java)
    }

}