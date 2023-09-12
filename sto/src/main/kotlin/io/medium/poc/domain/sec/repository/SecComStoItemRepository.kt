package io.medium.poc.domain.sec.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.com.model.entity.ComStoItem
import io.medium.poc.domain.sec.repository.custom.CustomSecComStoItemRepository

/**
 * 증권사 카테고리에 포함되는 api이기 때문에 sec 도메인에 작성했습니다.
 */
interface SecComStoItemRepository: BaseRepository<ComStoItem, String>, CustomSecComStoItemRepository {
    fun findByStoItemNo(stoItemNo: String): ComStoItem?
}
