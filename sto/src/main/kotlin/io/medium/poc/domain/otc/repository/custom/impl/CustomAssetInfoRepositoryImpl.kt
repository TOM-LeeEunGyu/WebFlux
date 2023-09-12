package io.medium.poc.domain.otc.repository.custom.impl

import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.com.model.entity.QComStoItem.comStoItem
import io.medium.poc.domain.otc.model.dto.OctAssetInfoDto
import io.medium.poc.domain.otc.model.dto.OctAssetInfoListDto
import io.medium.poc.domain.otc.repository.custom.CustomAssetInfoRepository
import io.medium.poc.domain.sec.model.entity.QSecCustAccount.secCustAccount
import io.medium.poc.domain.sec.model.entity.QSecCustAccountBalance.secCustAccountBalance

class CustomAssetInfoRepositoryImpl(
        private val query: JPAQueryFactory,
): CustomAssetInfoRepository {
    override fun assetInfo(custMgmtInstNo: String, custMgmtInstAcntNo: String): OctAssetInfoDto {
        return query.select(
                constructor(OctAssetInfoDto::class.java,
                        secCustAccount.depositAmount,
                        secCustAccount.trdMrgnAmt,

                ))
                .from(secCustAccount)
                .where(secCustAccount.compositeId.custMgmtInstNo.eq(custMgmtInstNo),
                        secCustAccount.compositeId.custMgmtInstAcntNo.eq(custMgmtInstAcntNo),
                        )
                .fetchOne() ?: OctAssetInfoDto()

    }



    override fun assetInfoDataList(
        custMgmtInstNo: String,
        custMgmtInstAcntNo: String,
        recordsCount: Long,
        bookmark:Long?
    ): List<OctAssetInfoListDto> {
        return query.select(constructor(OctAssetInfoListDto::class.java,
                secCustAccountBalance.compositeId.stoItemNo,
                comStoItem.stoItemName,
                secCustAccountBalance.balanceQty,
                secCustAccountBalance.trdMrgnQty,
                ))
                .from(secCustAccountBalance)
                .where(secCustAccountBalance.compositeId.custMgmtInstNo.eq(custMgmtInstNo),
                        secCustAccountBalance.compositeId.custMgmtInstAcntNo.eq(custMgmtInstAcntNo),
                        )
                .join(comStoItem).on(
                        secCustAccountBalance.compositeId.stoItemNo.eq(comStoItem.stoItemNo)
                )
                .limit(recordsCount + 1)
                .fetch()
    }

}
