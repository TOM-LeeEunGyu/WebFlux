package io.medium.poc.domain.sec.repository.custom

import io.medium.poc.domain.sec.model.entity.SecCashTokenTrading
import io.medium.poc.domain.sec.model.entity.SecCashTokenTradingMultiKeys
import java.time.LocalDate

/**
 * 증권사 캐시토큰거래내역 repository
 */
interface CustomSecCashTokenTradingRepository {

    fun retrieveTradeOrderNumber(): Long

    fun cashTokenTradingList(
        compositeId: SecCashTokenTradingMultiKeys,
        startTradeDtm: LocalDate,
        endTradeDtm: LocalDate
    ): List<SecCashTokenTrading>

}
