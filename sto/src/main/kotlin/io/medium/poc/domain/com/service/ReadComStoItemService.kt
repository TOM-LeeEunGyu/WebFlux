package io.medium.poc.domain.com.service

import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.common.code.TradeStartYn
import io.medium.poc.domain.com.model.dto.ComStoItemDto
import io.medium.poc.domain.com.repository.ComStoItemRepository
import org.springframework.stereotype.Service

/**
 * ComStoItem read 서비스
 * created by basquiat
 */
@Service
class ReadComStoItemService(
    private val comStoItemRepository: ComStoItemRepository,
) {

    fun checkExistsComStoItem(id: String): Boolean {
        return comStoItemRepository.existsById(id)
    }

    fun comStoItems(
        stoItemNo: String?,
        stoBaseAssetType: StoBaseAssetType?,
        trdStartYn: TradeStartYn?,
        recordsCount: Long,
        bookmark: String?,
    ): List<ComStoItemDto> {
        return comStoItemRepository.getComStoItems(stoItemNo, stoBaseAssetType, trdStartYn, recordsCount, bookmark)
    }

}
