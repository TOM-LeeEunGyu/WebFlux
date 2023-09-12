package io.medium.poc.api.usecase.otc

import io.medium.poc.api.controller.otc.request.query.OctEnforcementResultQuery
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.domain.otc.model.dto.OctEnforcementResultDto
import io.medium.poc.domain.otc.service.ReadOtcService
import org.springframework.stereotype.Service

@Service
class ReadOtcStoTradingUseCase(
    private val readOtcService: ReadOtcService,
) {
    fun getEnforcementResulList(query: OctEnforcementResultQuery): ResultResponsePagination<OctEnforcementResultDto> {
        val list = readOtcService.getEnforcementResulList(
                query.stoItemNo,
                query.custMgmtInstNo,
                query.custMgmtInstAcntNo,
                query.recordsCount,
                query.bookmark
        )
        if(list.isEmpty()) {
            return ResultResponsePagination.of(
                    last = true,
                    recordsCount = 0,
                    data = list,
            )
        }
        val result = list.take((query.recordsCount).toInt())

        val isLast = list.size <= query.recordsCount.toInt()

        val bookmark = if (!isLast) {
            0 // 임시값
        } else {
            null
        }

        return ResultResponsePagination.of(
                bookmark = bookmark.toString(),
                last = isLast,
                recordsCount = result.size.toLong(),
                data = result,
        )

    }

}
