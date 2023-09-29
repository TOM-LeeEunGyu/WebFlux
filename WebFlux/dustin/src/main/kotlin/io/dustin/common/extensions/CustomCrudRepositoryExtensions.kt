package io.dustin.common.extensions

import io.dustin.common.utils.CommonUtils.notFound
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

/**
 * 코틀린 확장함수를 활용한 커스텀 api 만들기
 * 어떤 데이터를 조회했을 때 null을 반환하는 것이 아닌 에러를 던져 전역 에러 핸들러를 통해 클라이언트에 전달하자
 * ex) 예를 들면 어떤 정보를 업데이트하기 위해 해당 정보를 가져올 때 비어있다면 정보의 아이디값이 잘못되었거나 없을 수 있다.
 * 이때는 클라이언트에 메세지를 던질 필요가 있다.
 *
 * 일반적인 스프링 부트의 경우
 * fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID, message: String? = null): T = this.findByIdOrNull(id) ?: throw IllegalArgumentException(message)
 *
 * webflux의 경우
 */
fun <T, ID> ReactiveCrudRepository<T, ID>.findByIdOrThrow(id: ID): Mono<T> {
    return this.findById(id)
        .switchIfEmpty { notFound("Id [$id]로 조회된 정보가 없습니다.") }

}