package io.medium.poc.domain.bank.repository.custom

import io.medium.poc.domain.bank.model.entity.BankCashTokenTrading
import io.medium.poc.domain.bank.model.entity.BankCashTokenTradingMultiKeys
import java.time.LocalDate

/**
 * 은행 캐시토큰거래 repository
 */
interface CustomBankCashTokenTradingRepository {

    fun retrieveTradeOrderNumber(
         bankInstNo: String,
         custMgmtInstNo: String,
         cashId: String,
         tradeDt: LocalDate
    ): Long

    fun cashTokenTradingList(
        compositeId: BankCashTokenTradingMultiKeys,
        startTradeDtm: LocalDate,
        endTradeDtm: LocalDate
    ): List<BankCashTokenTrading>

}
