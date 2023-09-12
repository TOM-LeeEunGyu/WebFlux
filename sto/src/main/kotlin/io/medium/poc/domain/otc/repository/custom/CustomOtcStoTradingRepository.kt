package io.medium.poc.domain.otc.repository.custom

import io.medium.poc.domain.otc.model.dto.OctEnforcementResultDto

interface CustomOtcStoTradingRepository {
    fun enforcementResult(
        stoItemNo:String?,
        custMgmtInstNo:String,
        custMgmtInstAcntNo:String,
        recordsCount: Long,
        bookmark: Long?
    ): List<OctEnforcementResultDto>

    fun otcStoTradingNo(): Long

}
