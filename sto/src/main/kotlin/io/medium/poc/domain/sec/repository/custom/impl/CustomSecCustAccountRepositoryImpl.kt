package io.medium.poc.domain.sec.repository.custom.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.com.model.entity.QComInvestor.comInvestor
import io.medium.poc.domain.com.model.entity.QComStoItem.comStoItem
import io.medium.poc.domain.sec.model.dto.AssetInfoDto
import io.medium.poc.domain.sec.model.entity.QSecAccountValidate.secAccountValidate
import io.medium.poc.domain.sec.model.entity.QSecCustAccount.secCustAccount
import io.medium.poc.domain.sec.model.entity.QSecCustAccountBalance.secCustAccountBalance
import io.medium.poc.domain.sec.model.entity.SecAccountValidate
import io.medium.poc.domain.sec.repository.custom.CustomSecCustAccountRepository
import java.time.LocalDate

class CustomSecCustAccountRepositoryImpl(
    private val query: JPAQueryFactory,
): CustomSecCustAccountRepository {
    override fun getAssetInformation(
        custMgmtInstNo: String,
        custMgmtInstAcntNo: String,
        recordsCount: Long,
        bookmark: Long
    ): List<AssetInfoDto> {
        return query.select(constructor(AssetInfoDto::class.java,
                secCustAccount.depositAmount,
                secCustAccount.trdMrgnAmt,
                secCustAccountBalance.compositeId.stoItemNo,
                comStoItem.stoItemName,
                secCustAccountBalance.balanceQty,
                secCustAccountBalance.trdMrgnQty,
        ))
            .from(comInvestor)
            .join(secCustAccount).on(
                secCustAccount.compositeId.custMgmtInstNo.eq(comInvestor.custMgmtInstNo)
                    .and(secCustAccount.compositeId.custMgmtInstAcntNo.eq(comInvestor.custMgmtInstAcntNo))
            )
            .join(secCustAccountBalance).on(
                secCustAccountBalance.compositeId.custMgmtInstNo.eq(comInvestor.custMgmtInstNo)
                    .and(secCustAccountBalance.compositeId.custMgmtInstAcntNo.eq(comInvestor.custMgmtInstAcntNo))
            )
            .join(comStoItem).on(
                comStoItem.stoItemNo.eq(secCustAccountBalance.compositeId.stoItemNo)
            )
            .where(
                comInvestor.custMgmtInstNo.eq(custMgmtInstNo),
                comInvestor.custMgmtInstAcntNo.eq(custMgmtInstAcntNo),
            )
            .limit(recordsCount + 1)
            .fetch()

    }

    override fun getSecAccountValidateList(
        reqDt: LocalDate,
        recordsCount: Long,
        bookmark: Long?
    ): List<SecAccountValidate> {
        return query.selectFrom(secAccountValidate)
                    .where(
                        secAccountValidate.reqDt.eq(reqDt),
                        byPaging(bookmark)
                    )
                    .orderBy(secAccountValidate.compositeId.confirmNo.asc())
                    .limit(recordsCount + 1)
                    .fetch()
    }

    private fun byPaging(bookmark: Long?): BooleanBuilder {
        val builder = BooleanBuilder()
        bookmark?.let {
            if(it > 0) {
                builder.and(secAccountValidate.compositeId.confirmNo.lt(it))
            }
        }
        return builder
    }
}