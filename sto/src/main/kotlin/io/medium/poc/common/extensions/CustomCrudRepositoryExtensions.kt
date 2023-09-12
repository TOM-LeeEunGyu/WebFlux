package io.medium.poc.common.extensions

import io.medium.poc.common.utils.notFoundEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

/**
 * null 을 반환하지 않고 NotFoundEntityException 을 날리자.
 * created by basquiat
 */
fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID, message: String? = null): T = this.findByIdOrNull(id) ?: notFoundEntity(message)