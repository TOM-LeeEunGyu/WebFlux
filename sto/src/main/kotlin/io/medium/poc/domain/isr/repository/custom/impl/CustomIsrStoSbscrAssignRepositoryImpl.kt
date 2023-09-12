package io.medium.poc.domain.isr.repository.custom.impl

import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.com.model.entity.QComStoItem.comStoItem
import io.medium.poc.domain.isr.model.dto.IsrInvestorInfoDto
import io.medium.poc.domain.isr.model.dto.IsrInvestorListDto
import io.medium.poc.domain.isr.model.entity.IsrStoSbscrAssign
import io.medium.poc.domain.isr.model.entity.QIsrStoIssueMgmt.isrStoIssueMgmt
import io.medium.poc.domain.isr.model.entity.QIsrStoSbscrAplct.isrStoSbscrAplct
import io.medium.poc.domain.isr.model.entity.QIsrStoSbscrAssign.isrStoSbscrAssign
import io.medium.poc.domain.isr.repository.custom.CustomIsrStoSbscrAssignRepository

class CustomIsrStoSbscrAssignRepositoryImpl(
        private val query: JPAQueryFactory,
): CustomIsrStoSbscrAssignRepository {

    override fun getIsrInvestors(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
        recordsCount: Long,
        bookmark:Long
    ): List<IsrInvestorListDto> {
        return query.select(constructor(IsrInvestorListDto::class.java,
                        isrStoIssueMgmt.issueAmt,
                        comStoItem.stoItemName,
                        isrStoSbscrAplct.compositeId.stoItemNo,
                        isrStoSbscrAplct.sbscrName,
                        isrStoSbscrAplct.compositeId.custMgmtInstNo,
                        isrStoSbscrAplct.compositeId.custMgmtInstAcntNo,
                        isrStoSbscrAplct.issueSbscrAplctQty,
                        isrStoSbscrAplct.issueSbscrMarginAmt,
                        isrStoSbscrAssign.sbscrAssignQty,
                        isrStoSbscrAssign.sbscrReturnAmt,
                        isrStoSbscrAssign.sbscrAssignDt,
                        isrStoSbscrAplct.sbscrMarginAmtPayYn,
                        isrStoSbscrAplct.acntValidateYn,
                        isrStoSbscrAplct.apprYn,
                        isrStoSbscrAssign.apprYn.`as`("apprYn2"),
                    ))
                    .from(isrStoSbscrAplct)
                    .leftJoin(isrStoSbscrAssign).on(
                        isrStoSbscrAssign.compositeId.issueMgmtInstNo.eq(isrStoSbscrAplct.compositeId.issueMgmtInstNo)
                        .and(isrStoSbscrAssign.compositeId.issueMgmtInstAcntNo.eq(isrStoSbscrAplct.compositeId.issueMgmtInstAcntNo))
                        .and(isrStoSbscrAssign.compositeId.custMgmtInstNo.eq(isrStoSbscrAplct.compositeId.custMgmtInstNo))
                        .and(isrStoSbscrAssign.compositeId.custMgmtInstAcntNo.eq(isrStoSbscrAplct.compositeId.custMgmtInstAcntNo))
                        .and(isrStoSbscrAssign.compositeId.stoItemNo.eq(isrStoSbscrAplct.compositeId.stoItemNo))
                    )
                    .join(comStoItem).on(
                        isrStoSbscrAplct.compositeId.stoItemNo.eq(comStoItem.stoItemNo)
                    )
                    .join(isrStoIssueMgmt).on(
                        isrStoSbscrAplct.compositeId.issueMgmtInstNo.eq(isrStoIssueMgmt.compositeId.issueMgmtInstNo)
                        .and(
                            isrStoSbscrAplct.compositeId.issueMgmtInstAcntNo.eq(isrStoIssueMgmt.compositeId.issueMgmtInstAcntNo)
                        )
                        .and(
                            isrStoSbscrAplct.compositeId.stoItemNo.eq(isrStoIssueMgmt.compositeId.stoItemNo)
                        )
                    )
                    .join(comStoItem).on(
                        isrStoSbscrAplct.compositeId.stoItemNo.eq(comStoItem.stoItemNo)
                    )
                    .where(
                        isrStoSbscrAplct.compositeId.issueMgmtInstNo.eq(issueMgmtInstNo),
                        isrStoSbscrAplct.compositeId.issueMgmtInstAcntNo.eq(issueMgmtInstAcntNo),
                        isrStoSbscrAplct.compositeId.stoItemNo.eq(stoItemNo),
                    )
                    .limit(recordsCount + 1)
                    .fetch()
    }

    override fun getIsrInvestorInfo(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String
    ): IsrInvestorInfoDto {
            return query.select(constructor(IsrInvestorInfoDto::class.java,
                isrStoSbscrAplct.count().`as`("issueSbscrPersonCnt"),
                isrStoIssueMgmt.issueAmt,
                isrStoSbscrAplct.issueSbscrMarginAmt.sum().`as`("issueSbscrMarginAmt"),
                isrStoSbscrAssign.sbscrReturnAmt.sum().`as`("sbscrReturnAmt"),
            ))
            .from(isrStoSbscrAplct)
            .leftJoin(isrStoSbscrAssign).on(
                isrStoSbscrAplct.compositeId.issueMgmtInstNo.eq(isrStoSbscrAssign.compositeId.issueMgmtInstNo)
                .and(isrStoSbscrAplct.compositeId.issueMgmtInstAcntNo.eq(isrStoSbscrAssign.compositeId.issueMgmtInstAcntNo))
                .and(isrStoSbscrAplct.compositeId.custMgmtInstNo.eq(isrStoSbscrAssign.compositeId.custMgmtInstNo))
                .and(isrStoSbscrAplct.compositeId.custMgmtInstAcntNo.eq(isrStoSbscrAssign.compositeId.custMgmtInstAcntNo))
                .and(isrStoSbscrAssign.compositeId.stoItemNo.eq(isrStoSbscrAplct.compositeId.stoItemNo))

            )
            .join(isrStoIssueMgmt).on(
                isrStoSbscrAplct.compositeId.issueMgmtInstNo.eq(isrStoIssueMgmt.compositeId.issueMgmtInstNo)
                .and(isrStoSbscrAplct.compositeId.issueMgmtInstAcntNo.eq(isrStoIssueMgmt.compositeId.issueMgmtInstAcntNo))
                .and(isrStoSbscrAplct.compositeId.stoItemNo.eq(isrStoIssueMgmt.compositeId.stoItemNo))
            )
            .where(
                isrStoSbscrAplct.compositeId.issueMgmtInstNo.eq(issueMgmtInstNo),
                isrStoSbscrAplct.compositeId.issueMgmtInstAcntNo.eq(issueMgmtInstAcntNo),
                isrStoSbscrAplct.compositeId.stoItemNo.eq(stoItemNo)
            )
            .fetchOne() ?: IsrInvestorInfoDto()
    }

    override fun batchForIsrStoSbscrAssigns(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String
    ): List<IsrStoSbscrAssign> {
        return query.selectFrom(isrStoSbscrAssign)
                    .where(
                        isrStoSbscrAssign.compositeId.issueMgmtInstNo.eq(issueMgmtInstNo),
                        isrStoSbscrAssign.compositeId.issueMgmtInstAcntNo.eq(issueMgmtInstAcntNo),
                        isrStoSbscrAssign.compositeId.stoItemNo.eq(stoItemNo)
                    )
                    .fetch()
    }

}
