package io.medium.poc.domain.com.service

import io.medium.poc.domain.com.model.entity.ComStoItem
import io.medium.poc.domain.com.repository.ComStoItemRepository
import org.springframework.stereotype.Service

/**
 * ComStoItem write 서비스
 * created by basquiat
 */
@Service
class WriteComStoItemService(
    private val comStoItemRepository: ComStoItemRepository,
) {

    fun saveComStoItem(entity: ComStoItem): ComStoItem {
        return comStoItemRepository.save(entity)
    }

}
