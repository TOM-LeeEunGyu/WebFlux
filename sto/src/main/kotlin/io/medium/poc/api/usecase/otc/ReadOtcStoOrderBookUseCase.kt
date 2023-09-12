package io.medium.poc.api.usecase.otc

import io.medium.poc.api.controller.otc.request.query.OctPriceCheckQuery
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.common.utils.lastAt
import io.medium.poc.domain.otc.model.dto.OctPriceCheckDto
import io.medium.poc.domain.otc.service.ReadOtcService
import org.springframework.stereotype.Service

@Service
class ReadOtcStoOrderBookUseCase(
    private val readOtcService: ReadOtcService,
) {
    fun getPriceCheckList(query: OctPriceCheckQuery): ResultResponsePagination<OctPriceCheckDto> {
        val list = with(query) {
            readOtcService.getPriceCheckList(
                stoItemNo,
                orderDt,
                orderType(),
                orderCkYn(),
                tradingYn(),
                recordsCount,
                bookmark
            )
        }
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
            result.lastAt().orderNo
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
