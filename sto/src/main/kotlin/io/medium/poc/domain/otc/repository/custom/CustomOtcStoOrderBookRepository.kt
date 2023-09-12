package io.medium.poc.domain.otc.repository.custom

import io.medium.poc.common.code.OrderCheckYn
import io.medium.poc.common.code.OrderType
import io.medium.poc.common.code.TradingYn
import io.medium.poc.domain.otc.model.dto.OctPriceCheckDto
import io.medium.poc.domain.otc.model.entity.OtcStoOrderBook
import java.time.LocalDate

interface CustomOtcStoOrderBookRepository {
    fun getPriceCheckList(
        stoItemNo: String?,
        orderDt: LocalDate?,
        orderType: OrderType?,
        orderCkYn: OrderCheckYn?,
        tradingYn: TradingYn?,
        recordsCount: Long,
        bookmark: Long?
    ):List<OctPriceCheckDto>

    fun otcStoOrderBookNo(): Long

    fun getOtcStoOrderBookByOrderNo(orderNo: Long): OtcStoOrderBook?

}
