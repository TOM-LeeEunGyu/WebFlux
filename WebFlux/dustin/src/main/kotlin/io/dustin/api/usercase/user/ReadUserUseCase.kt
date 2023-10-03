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
     * 코드에서 보면 페이징 처리한 결과값에서 collectList를 통해서 하나의 Flux를 모으고 Flux연산자가 제공하는 zipWith를 통해서 뮤지션의 전체 카운트를 가져온다.
     * zipWith는 처음 작업한 뮤지션의 Flux와 해당 메소드에서 실행된 전체 카운트 Mono를 튜플 형식으로 반환한다.
     * 코드를 보면 알겠지만 map에서 tuple을 받아서 PageImpl을 통해서 Mono<Page<Musician>>형태로 반환한다.
     * (이해안됨)
     */
    fun usersByQuery(queryPage: QueryPage, matrixVariable: MultiValueMap<String, Any>): Mono<Page<User>> {
        val match = createQuery(matrixVariable)
        return read.usersByQuery(queryPage.pagination(match))
            .collectList()
            .zipWith(read.totalCountByQuery(match))
            .map { tuple -> PageImpl(tuple.t1, queryPage.fromPageable(), tuple.t2) }
    }

}