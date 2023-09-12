package io.medium.poc.domain.com.repository.custom.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.common.code.TradeStartYn
import io.medium.poc.domain.com.model.dto.ComStoItemDto
import io.medium.poc.domain.com.model.entity.QComStoItem.comStoItem
import io.medium.poc.domain.com.repository.custom.CustomComStoItemRepository

class CustomComStoItemRepositoryImpl(
    private val query: JPAQueryFactory,
): CustomComStoItemRepository {

    override fun getComStoItems(
        stoItemNo: String?,
        stoBaseAssetType: StoBaseAssetType?,
        trdStartYn: TradeStartYn?,
        recordsCount: Long,
        bookmark: String?,
    ): List<ComStoItemDto> {
        return query.select(constructor(ComStoItemDto::class.java,
                        comStoItem.stoItemNo,
                        comStoItem.stoItemName,
                        comStoItem.issueMgmtInstNo,
                        comStoItem.issueMgmtInstAcntNo,
                        comStoItem.stoBaseAssetType,
                        comStoItem.tradeStartYn,
                        comStoItem.incinerationYn,
                    ))
                    .from(comStoItem)
                    .where(
                        searchCondition(stoItemNo, stoBaseAssetType, trdStartYn, bookmark),
                    )
                    .orderBy(comStoItem.stoItemNo.desc())
                    .limit(recordsCount + 1)
                    .fetch()
    }

    private fun searchCondition(
        stoItemNo: String?,
        stoBaseAssetType: StoBaseAssetType?,
        trdStartYn: TradeStartYn?,
        bookmark: String?
    ): BooleanBuilder {
        val builder = BooleanBuilder()
        stoItemNo?.let {
            if(it.isNotBlank()) {
                builder.and(comStoItem.stoItemNo.eq(it))
            }
        }
        stoBaseAssetType?.let {
            builder.and(comStoItem.stoBaseAssetType.eq(it))
        }
        trdStartYn?.let {
            builder.and(comStoItem.tradeStartYn.eq(it))
        }
        bookmark?.let {
            if(it.isNotBlank()) {
                builder.and(comStoItem.stoItemNo.lt(it))
            }
        }
        return builder
    }

}
