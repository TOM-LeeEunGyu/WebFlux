package io.medium.poc.domain.com.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.com.model.entity.ComStoItem
import io.medium.poc.domain.com.repository.custom.CustomComStoItemRepository

/**
 * ComStoItem Repository
 * created by basquiat
 */
interface ComStoItemRepository: BaseRepository<ComStoItem, String>, CustomComStoItemRepository {
    override fun existsById(id: String): Boolean
}
