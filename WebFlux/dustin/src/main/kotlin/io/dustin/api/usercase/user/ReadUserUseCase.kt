package io.dustin.api.usercase.user

import io.dustin.common.builder.createQuery
import io.dustin.common.model.request.QueryPage
import io.dustin.domain.user.model.User
import io.dustin.domain.user.service.ReadUserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import reactor.core.publisher.Mono

@Service
class ReadUserUseCase(
    private val read: ReadUserService,
) {

    fun userById(id: Long): Mono<User> {
        return read.userByIdOrThrow(id)
    }

    /**
     * collectList를 사용하여 리스트로 수집한다
     * zipWith 스트립 항목을 조합하여 새로운 스트립을 생성 한다(스트림을 병렬로 실행할 수 있다.)
     */
    fun usersByQuery(queryPage: QueryPage, matrixVariable: MultiValueMap<String, Any>): Mono<Page<User>> {
        val match = createQuery(matrixVariable)
        return read.usersByQuery(queryPage.pagination(match))
            .collectList()
            .zipWith(read.totalCountByQuery(match))
            .map { tuple -> PageImpl(tuple.t1, queryPage.fromPageable(), tuple.t2) }
    }

}