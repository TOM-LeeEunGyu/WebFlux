package io.medium.poc.domain.com.repository.custom

import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.common.code.TradeStartYn
import io.medium.poc.domain.com.model.dto.ComStoItemDto

interface CustomComStoItemRepository {

    fun getComStoItems(
        stoItemNo: String?,
        stoBaseAssetType: StoBaseAssetType?,
        trdStartYn: TradeStartYn?,
        recordsCount: Long,
        bookmark: String?,
    ): List<ComStoItemDto>

}
