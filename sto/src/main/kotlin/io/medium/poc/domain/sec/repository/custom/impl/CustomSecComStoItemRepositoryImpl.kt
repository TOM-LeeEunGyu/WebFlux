package io.medium.poc.domain.sec.repository.custom.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.sec.model.entity.QSecCustAccountBalance.secCustAccountBalance
import io.medium.poc.domain.sec.repository.custom.CustomSecComStoItemRepository
import java.math.BigDecimal

class CustomSecComStoItemRepositoryImpl(
    private val query: JPAQueryFactory,
): CustomSecComStoItemRepository {

    override fun balanceQtyQuery(stoItemNo: String, custMgmtInstNo: String): BigDecimal? {
        return query.select(
                        secCustAccountBalance.balanceQty.sum().`as`("balanceQty"),
                    )
                    .from(secCustAccountBalance)
                    .where(
                        secCustAccountBalance.compositeId.stoItemNo.eq(stoItemNo),
                        secCustAccountBalance.compositeId.custMgmtInstNo.eq(custMgmtInstNo)
                    )
                    .fetchOne()
    }


}