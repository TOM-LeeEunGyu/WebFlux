package io.dustin.api.usercase.mugi

import io.dustin.common.model.request.QueryPage
import io.dustin.domain.mugi.model.Mugi
import io.dustin.domain.mugi.service.ReadMugiService
import io.dustin.domain.user.service.ReadUserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ReadRecordUseCase(
    private val read: ReadMugiService,
    private val readMusician: ReadUserService,
) {

    fun recordById(id: Long): Mono<Mugi> {
        return read.mugiByIdOrThrow(id)
    }

    fun recordByMusicianId(queryPage: QueryPage, userId: Long): Mono<Page<Mugi>> {
        val user = readMusician.userByIdOrThrow(userId)
        return user.flatMapMany { user ->
            read.mugiByUserId(userId, queryPage.fromPageable())
                .map {
                    it.user = user
                    it
                }
        }
            .collectList()
            .zipWith(read.mugiCountByUser(userId))
            .map { tuple -> PageImpl(tuple.t1, queryPage.fromPageable(), tuple.t2) }

    }

    fun allRecords(queryPage: QueryPage, matrixVariable: MultiValueMap<String, Any>): Flux<Mugi> {
        val prefix = "mugi"
        val clazz = Record::class
        val whereClause = createNativeWhereClause(prefix, clazz, matrixVariable)
        val (orderSql, limitSql) = createNativeSortLimitClause(prefix, clazz, queryPage)
        return read.allRecords(whereClause = whereClause, orderClause = orderSql, limitClause = limitSql)
    }

}