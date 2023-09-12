package io.medium.poc.domain.bank.repository.custom.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.bank.model.entity.BankCashTokenTrading
import io.medium.poc.domain.bank.model.entity.BankCashTokenTradingMultiKeys
import io.medium.poc.domain.bank.model.entity.QBankCashTokenTrading.bankCashTokenTrading
import io.medium.poc.domain.bank.repository.custom.CustomBankCashTokenTradingRepository
import java.time.LocalDate

class CustomBankCashTokenTradingRepositoryImpl(
    private val query: JPAQueryFactory,
): CustomBankCashTokenTradingRepository {

    override fun retrieveTradeOrderNumber(
        bankInstNo: String,
        custMgmtInstNo: String,
        cashId: String,
        tradeDt: LocalDate
    ): Long {
        val result = query.select(
                                bankCashTokenTrading.compositeId.tradeOrder
                            )
                            .from(bankCashTokenTrading)
                            .where(
                                bankCashTokenTrading.compositeId.bankInstNo.eq(bankInstNo),
                                bankCashTokenTrading.compositeId.custMgmtInstNo.eq(custMgmtInstNo),
                                bankCashTokenTrading.compositeId.cashId.eq(cashId),
                                bankCashTokenTrading.compositeId.tradeDt.eq(tradeDt)
                            )
                            .orderBy(bankCashTokenTrading.compositeId.tradeOrder.desc())
                            .fetchFirst()
        return result?.let { it + 1 } ?: 1
    }

    override fun cashTokenTradingList(
        compositeId: BankCashTokenTradingMultiKeys,
        startTradeDtm: LocalDate,
        endTradeDtm: LocalDate
    ): List<BankCashTokenTrading> {
        return query.selectFrom(bankCashTokenTrading)
                    .where(
                        bankCashTokenTrading.compositeId.bankInstNo.eq(compositeId.bankInstNo),
                        bankCashTokenTrading.compositeId.custMgmtInstNo.eq(compositeId.custMgmtInstNo),
                        bankCashTokenTrading.compositeId.cashId.eq(compositeId.cashId),
                        bankCashTokenTrading.compositeId.tradeDt.between(startTradeDtm, endTradeDtm)
                    )
                    .fetch()
    }

}
