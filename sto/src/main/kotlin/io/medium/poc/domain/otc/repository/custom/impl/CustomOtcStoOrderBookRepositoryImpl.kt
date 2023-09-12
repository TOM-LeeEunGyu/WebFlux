package io.medium.poc.domain.otc.repository.custom.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.common.code.OrderCheckYn
import io.medium.poc.common.code.OrderType
import io.medium.poc.common.code.TradingYn
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.domain.com.model.entity.QComStoItem.comStoItem
import io.medium.poc.domain.otc.model.dto.OctPriceCheckDto
import io.medium.poc.domain.otc.model.entity.OtcStoOrderBook
import io.medium.poc.domain.otc.model.entity.QOtcStoOrderBook.otcStoOrderBook
import io.medium.poc.domain.otc.repository.custom.CustomOtcStoOrderBookRepository
import java.time.LocalDate

class CustomOtcStoOrderBookRepositoryImpl(
    private val query: JPAQueryFactory,
): CustomOtcStoOrderBookRepository {
    override fun getPriceCheckList(
        stoItemNo: String?,
        orderDt: LocalDate?,
        orderType: OrderType?,
        orderCkYn: OrderCheckYn?,
        tradingYn: TradingYn?,
        recordsCount: Long,
        bookmark:Long?
    ): List<OctPriceCheckDto> {
        return query.select(constructor(OctPriceCheckDto::class.java,
                        otcStoOrderBook.compositeId.orderNo,
                        otcStoOrderBook.compositeId.stoItemNo,
                        comStoItem.stoItemName,
                        otcStoOrderBook.custMgmtInstNo,
                        otcStoOrderBook.custMgmtInstAcntNo,
                        otcStoOrderBook.orderType,
                        otcStoOrderBook.orderPrice,
                        otcStoOrderBook.orderQty,
                        otcStoOrderBook.orderDtm,
                    ))
                    .from(otcStoOrderBook)
                    .leftJoin(comStoItem).on(comStoItem.stoItemNo.eq(otcStoOrderBook.compositeId.stoItemNo))
                    .where(
                        otcStoOrderBook.orderCancelYn.isNull.or(
                            otcStoOrderBook.orderCancelYn.eq(YesOrNo.N)
                        ),
                        searchCondition(stoItemNo, orderDt, orderType, orderCkYn, tradingYn, bookmark)
                    )
                    .orderBy(otcStoOrderBook.compositeId.orderNo.desc())
                    .limit(recordsCount + 1)
                    .fetch()
    }

    private fun searchCondition(
        stoItemNo: String?,
        orderDt: LocalDate?,
        orderType: OrderType?,
        orderCkYn: OrderCheckYn?,
        tradingYn: TradingYn?,
        bookmark: Long?
    ): BooleanBuilder {
        val builder = BooleanBuilder()

        stoItemNo?.let {
            if(it.isNotBlank()) {
                builder.and(otcStoOrderBook.compositeId.stoItemNo.eq(stoItemNo))
            }
        }
        orderDt?.let {
            builder.and(otcStoOrderBook.compositeId.orderDt.eq(orderDt))
        }

        orderType?.let {
            builder.and(otcStoOrderBook.orderType.eq(orderType))
        }

        orderCkYn?.let {
            if(orderCkYn == OrderCheckYn.NOT_ORDER) {
                builder.and(
                    otcStoOrderBook.orderCkYn.isNull
                    .or(
                        otcStoOrderBook.orderCkYn.eq(orderCkYn)
                    )
                )
            } else {
                builder.and(otcStoOrderBook.orderCkYn.eq(orderCkYn))
            }
        }

        tradingYn?.let {
            builder.and(otcStoOrderBook.tradingYn.eq(tradingYn))
        }

        bookmark?.let {
            if(it > 0) {
                builder.and(otcStoOrderBook.compositeId.orderNo.lt(it))
            }
        }
        return builder
    }

    override fun otcStoOrderBookNo(): Long {
        val result = query.select(
            otcStoOrderBook.compositeId.orderNo.max()
        )
            .from(otcStoOrderBook)
            .fetchFirst()
        return result?.let { it + 1 } ?: 1
    }

    override fun getOtcStoOrderBookByOrderNo(orderNo: Long): OtcStoOrderBook? {
        return query.selectFrom(otcStoOrderBook)
                    .from(otcStoOrderBook)
                    .where(otcStoOrderBook.compositeId.orderNo.eq(orderNo))
                    .fetchOne()
    }

}
