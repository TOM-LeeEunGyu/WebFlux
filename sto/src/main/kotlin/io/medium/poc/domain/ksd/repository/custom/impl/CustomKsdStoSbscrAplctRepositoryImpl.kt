package io.medium.poc.domain.ksd.repository.custom.impl

import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.ksd.model.dto.KsdStoSbscrAplctInfoDto
import io.medium.poc.domain.ksd.model.dto.KsdStoSbscrAplctListDto
import io.medium.poc.domain.ksd.model.entity.QKsdStoIssueMgmt.ksdStoIssueMgmt
import io.medium.poc.domain.ksd.model.entity.QKsdStoSbscrAplct.ksdStoSbscrAplct
import io.medium.poc.domain.ksd.model.entity.QKsdStoSbscrAssign.ksdStoSbscrAssign
import io.medium.poc.domain.ksd.repository.custom.CustomKsdStoSbscrAplctRepository

class CustomKsdStoSbscrAplctRepositoryImpl(
    private val query: JPAQueryFactory,
): CustomKsdStoSbscrAplctRepository {

    override fun ksdStoSbscrApplications(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
        recordsCount: Long,
        bookmark: Long,
    ): List<KsdStoSbscrAplctListDto> {
        return query.select(constructor(KsdStoSbscrAplctListDto::class.java,
                        ksdStoIssueMgmt.issueAmt,
                        ksdStoSbscrAplct.compositeId.stoItemNo,
                        ksdStoSbscrAplct.sbscrName,
                        ksdStoSbscrAplct.compositeId.custMgmtInstNo,
                        ksdStoSbscrAplct.compositeId.custMgmtInstAcntNo,
                        ksdStoSbscrAplct.issueSbscrAplctQty,
                        ksdStoSbscrAplct.issueSbscrMarginAmt,
                        ksdStoSbscrAssign.sbscrAssignQty,
                        ksdStoSbscrAssign.sbscrReturnAmt,
                        ksdStoSbscrAssign.sbscrAssignDt,
                        ksdStoSbscrAplct.sbscrMarginAmtPayYn,
                        ksdStoSbscrAplct.acntValidateYn,
                        ksdStoSbscrAplct.apprYn,
                        ksdStoSbscrAssign.apprYn.`as`("apprYn2"),
                    ))
                    .from(ksdStoSbscrAplct)
                    .join(ksdStoSbscrAssign).on(
                        ksdStoSbscrAplct.compositeId.issueMgmtInstNo
                        .eq(ksdStoSbscrAssign.compositeId.issueMgmtInstNo)
                        .and(
                            ksdStoSbscrAplct.compositeId.issueMgmtInstAcntNo
                                .eq(ksdStoSbscrAssign.compositeId.issueMgmtInstAcntNo)
                        )
                        .and(
                            ksdStoSbscrAplct.compositeId.custMgmtInstNo
                                .eq(ksdStoSbscrAssign.compositeId.custMgmtInstNo)
                        )
                        .and(
                            ksdStoSbscrAplct.compositeId.custMgmtInstAcntNo
                                .eq(ksdStoSbscrAssign.compositeId.custMgmtInstAcntNo)
                        )
                        .and(
                            ksdStoSbscrAplct.compositeId.stoItemNo
                                .eq(ksdStoSbscrAssign.compositeId.stoItemNo)
                        )
                    )
                    .leftJoin(ksdStoIssueMgmt).on(
                        ksdStoSbscrAplct.compositeId.issueMgmtInstNo
                            .eq(ksdStoIssueMgmt.compositeId.issueMgmtInstNo)
                            .and(
                                ksdStoSbscrAplct.compositeId.issueMgmtInstAcntNo
                                .eq(ksdStoIssueMgmt.compositeId.issueMgmtInstAcntNo)
                            )
                            .and(
                                ksdStoSbscrAplct.compositeId.stoItemNo
                                    .eq(ksdStoIssueMgmt.compositeId.stoItemNo)
                            )
                    )
                    .where(
                        ksdStoSbscrAplct.compositeId.issueMgmtInstNo.eq(issueMgmtInstNo),
                        ksdStoSbscrAplct.compositeId.issueMgmtInstAcntNo.eq(issueMgmtInstAcntNo),
                        ksdStoSbscrAplct.compositeId.stoItemNo.eq(stoItemNo)
                    )
                    .orderBy(ksdStoSbscrAplct.issueSbscrAplctDt.desc())
                    .limit(recordsCount + 1)
                    .fetch()
    }

    override fun ksdStoSbscrAplctInfo(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String
    ): KsdStoSbscrAplctInfoDto {
        return query.select(constructor(KsdStoSbscrAplctInfoDto::class.java,
                        ksdStoSbscrAplct.compositeId.count(),
                        ksdStoSbscrAplct.issueSbscrMarginAmt.sum(),
                        ksdStoSbscrAssign.sbscrReturnAmt.sum()
                    ))
                    .from(ksdStoSbscrAplct)
                    .join(ksdStoSbscrAssign).on(
                        ksdStoSbscrAplct.compositeId.issueMgmtInstNo
                            .eq(ksdStoSbscrAssign.compositeId.issueMgmtInstNo)
                            .and(
                                ksdStoSbscrAplct.compositeId.issueMgmtInstAcntNo
                                    .eq(ksdStoSbscrAssign.compositeId.issueMgmtInstAcntNo)
                            )
                            .and(
                                ksdStoSbscrAplct.compositeId.custMgmtInstNo
                                    .eq(ksdStoSbscrAssign.compositeId.custMgmtInstNo)
                            )
                            .and(
                                ksdStoSbscrAplct.compositeId.custMgmtInstAcntNo
                                    .eq(ksdStoSbscrAssign.compositeId.custMgmtInstAcntNo)
                            )
                            .and(
                                ksdStoSbscrAplct.compositeId.stoItemNo
                                    .eq(ksdStoSbscrAssign.compositeId.stoItemNo)
                            )
                    )
                    .where(
                        ksdStoSbscrAplct.compositeId.issueMgmtInstNo.eq(issueMgmtInstNo),
                        ksdStoSbscrAplct.compositeId.issueMgmtInstAcntNo.eq(issueMgmtInstAcntNo),
                        ksdStoSbscrAplct.compositeId.stoItemNo.eq(stoItemNo)
                    )
                    .fetchOne() ?: KsdStoSbscrAplctInfoDto()
    }

}
