package io.medium.poc.domain.otc.repository.custom.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.com.model.entity.QComStoItem.comStoItem
import io.medium.poc.domain.otc.model.dto.OctEnforcementResultDto
import io.medium.poc.domain.otc.model.entity.QOtcStoTrading.otcStoTrading
import io.medium.poc.domain.otc.repository.custom.CustomOtcStoTradingRepository

class CustomOtcStoTradingRepositoryImpl(
    private val query: JPAQueryFactory,
): CustomOtcStoTradingRepository {

    override fun enforcementResult(
        stoItemNo: String?,
        custMgmtInstNo: String,
        custMgmtInstAcntNo: String,
        recordsCount: Long,
        bookmark: Long?
    ): List<OctEnforcementResultDto> {
        return query.select(constructor(OctEnforcementResultDto::class.java,
                        otcStoTrading.compositeId.stoItemNo,
                        comStoItem.stoItemName,
                        otcStoTrading.tradeType,
                        otcStoTrading.tradeQty,
                        otcStoTrading.trdAmt,
                        otcStoTrading.tradeDtm,
                    ))
                    .from(otcStoTrading)
                    .join(comStoItem).on(
                        otcStoTrading.compositeId.stoItemNo.eq(comStoItem.stoItemNo)
                    )
                    .where(
                        searchCondition(stoItemNo, custMgmtInstNo, custMgmtInstAcntNo)
                    )
                    .orderBy(otcStoTrading.compositeId.trdNo.desc())
                    .limit(recordsCount + 1)
                    .fetch()

    }

    private fun searchCondition(
        stoItemNo: String?,
        custMgmtInstNo: String,
        custMgmtInstAcntNo: String
    ): BooleanBuilder {
        val builder = BooleanBuilder()
        builder.and(
            otcStoTrading.compositeId.selCustMgmtInstNo.eq(custMgmtInstNo)
            .or(
                otcStoTrading.compositeId.buyCustMgmtInstNo.eq(custMgmtInstNo)
            )
        )
        builder.and(
            otcStoTrading.compositeId.selCustMgmtInstAcntNo.eq(custMgmtInstAcntNo)
                .or(
                    otcStoTrading.compositeId.buyCustMgmtInstAcntNo.eq(custMgmtInstAcntNo)
                )
        )

        stoItemNo?.let {
            if(it.isNotBlank()) {
                builder.and(otcStoTrading.compositeId.stoItemNo.eq(it))
            }
        }
        return builder
    }

    override fun otcStoTradingNo(): Long {
        val result = query.select(
                otcStoTrading.compositeId.trdNo.max()
        )
                .from(otcStoTrading)
                .fetchFirst()
        return result?.let { it + 1 } ?: 1
    }

}
