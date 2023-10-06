package io.dustin.common.extensions

import io.dustin.common.utils.CommonUtils.notFound
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

/**
 * 메세지가 필요하다면 메세지를 받을 수 있고 메세지가 없다면 정해진 메세지를 던질 수 있도록 처리한다.
 */
fun <T, ID> R2dbcRepository<T, ID>.findByIdOrThrow(id: ID, message: String? = null): Mono<T> {
    return this.findById(id)
               .switchIfEmpty { notFound(message?.let{ it } ?: "Id [$id]로 조회된 정보가 없습니다.") }

}