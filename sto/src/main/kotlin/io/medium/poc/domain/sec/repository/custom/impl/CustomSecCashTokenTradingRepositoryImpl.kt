package io.medium.poc.domain.sec.repository.custom.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.sec.model.entity.QSecCashTokenTrading.secCashTokenTrading
import io.medium.poc.domain.sec.model.entity.SecCashTokenTrading
import io.medium.poc.domain.sec.model.entity.SecCashTokenTradingMultiKeys
import io.medium.poc.domain.sec.repository.custom.CustomSecCashTokenTradingRepository
import java.time.LocalDate

class CustomSecCashTokenTradingRepositoryImpl(
    private val query: JPAQueryFactory,
): CustomSecCashTokenTradingRepository {

    override fun retrieveTradeOrderNumber(): Long {
        val result = query.select(
                                    secCashTokenTrading.compositeId.tradeOrder.max()
                                 )
                                 .from(secCashTokenTrading)
                                 .fetchFirst()
        return result?.let { it + 1 } ?: 1
    }

    override fun cashTokenTradingList(
        compositeId: SecCashTokenTradingMultiKeys,
        startTradeDtm: LocalDate,
        endTradeDtm: LocalDate
    ): List<SecCashTokenTrading> {
        return query.selectFrom(secCashTokenTrading)
            .where(
                secCashTokenTrading.compositeId.bankInstNo.eq(compositeId.bankInstNo),
                secCashTokenTrading.compositeId.custMgmtInstNo.eq(compositeId.custMgmtInstNo),
                secCashTokenTrading.compositeId.cashId.eq(compositeId.cashId),
                secCashTokenTrading.compositeId.tradeDt.between(startTradeDtm, endTradeDtm)
            )
            .fetch()
    }

}