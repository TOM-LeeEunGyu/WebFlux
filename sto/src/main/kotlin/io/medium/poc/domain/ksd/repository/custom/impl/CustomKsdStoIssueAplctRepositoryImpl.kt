package io.medium.poc.domain.ksd.repository.custom.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.ksd.model.dto.KsdPostingInfoDto
import io.medium.poc.domain.ksd.model.dto.KsdStoIssueAplctDto
import io.medium.poc.domain.ksd.model.entity.QKsdStoIssue.ksdStoIssue
import io.medium.poc.domain.ksd.model.entity.QKsdStoIssueAplct.ksdStoIssueAplct
import io.medium.poc.domain.ksd.model.entity.QKsdStoIssueMgmt.ksdStoIssueMgmt
import io.medium.poc.domain.ksd.repository.custom.CustomKsdStoIssueAplctRepository
import java.math.BigDecimal

class CustomKsdStoIssueAplctRepositoryImpl(
    private val query: JPAQueryFactory,
): CustomKsdStoIssueAplctRepository {

    override fun ksdStoIssueApplications(
        search: String,
        recordsCount: Long,
        bookmark: String?
    ): List<KsdStoIssueAplctDto> {
        return query.select(constructor(KsdStoIssueAplctDto::class.java,
                        ksdStoIssueAplct.compositeId.issueMgmtInstNo,
                        ksdStoIssueAplct.compositeId.issueMgmtInstAcntNo,
                        ksdStoIssueAplct.compositeId.issueNo,
                        ksdStoIssueAplct.stoItemName,
                        ksdStoIssueAplct.stoItemNo,
                        ksdStoIssueAplct.issueApprQty.`as`("issueProposalQty"),
                        ksdStoIssueMgmt.issueApprQty.coalesce(BigDecimal.ZERO).`as`("issueApproveQty"),
                        ksdStoIssue.issueName,
                        ksdStoIssueAplct.issueAplctDt,
                        ksdStoIssue.lstIssueDt,
                        ksdStoIssueMgmt.issueStatusType,
                        ksdStoIssueAplct.apprYn,
                        ksdStoIssueAplct.sndYn
                    ))
                    .from(ksdStoIssueAplct)
                    .join(ksdStoIssue).on(ksdStoIssueAplct.compositeId.issueNo.eq(ksdStoIssue.issueNo))
                    .leftJoin(ksdStoIssueMgmt).on(
                        ksdStoIssueAplct.compositeId.issueMgmtInstNo.eq(
                            ksdStoIssueMgmt.compositeId.issueMgmtInstNo
                        )
                        .and(
                            ksdStoIssueAplct.compositeId.issueMgmtInstAcntNo.eq(ksdStoIssueMgmt.compositeId.issueMgmtInstAcntNo)
                        )
                        .and(
                            ksdStoIssueAplct.stoItemNo.eq(ksdStoIssueMgmt.compositeId.stoItemNo)
                        )
                    )
                    .where(
                        searchCondition(search, bookmark)
                    )
                    .orderBy(ksdStoIssueAplct.compositeId.issueNo.desc())
                    .limit(recordsCount + 1)
                    .fetch()
    }

    override fun ksdStoIssuePostingInformation(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): KsdPostingInfoDto? {
        return query.select(constructor(KsdPostingInfoDto::class.java,
                        ksdStoIssueAplct.stoItemName,
                        ksdStoIssueAplct.stoItemNo,
                        ksdStoIssueAplct.issueGoodsEvlAmt,
                        ksdStoIssueAplct.stoBaseAssetType,
                        ksdStoIssueMgmt.issueAmt.coalesce(BigDecimal.ZERO),
                        ksdStoIssueMgmt.issueSbscrAplctStartDt,
                        ksdStoIssueMgmt.issueSbscrAplctEndDt,
                        ksdStoIssueMgmt.issueSbscrAssignDt,
                        ksdStoIssueMgmt.issueApprQty,
                        ksdStoIssueMgmt.issueDt,
                        ksdStoIssue.issueName,
                        ksdStoIssue.issueAddr,
                        ksdStoIssue.issueEmail,
                        ksdStoIssue.issueRprsnName,
                        ksdStoIssue.issueHomepage,
                        ksdStoIssueMgmt.businessProfileData,
                        ksdStoIssueMgmt.prospectusData,
                        ksdStoIssueMgmt.apprYn
                    ))
                    .from(ksdStoIssueAplct)
                    .leftJoin(ksdStoIssueMgmt).on(
                        ksdStoIssueAplct.compositeId.issueMgmtInstNo.eq(ksdStoIssueMgmt.compositeId.issueMgmtInstNo)
                        .and(
                            ksdStoIssueAplct.compositeId.issueMgmtInstAcntNo.eq(ksdStoIssueMgmt.compositeId.issueMgmtInstAcntNo)
                        )
                        .and(
                            ksdStoIssueAplct.stoItemNo.eq(ksdStoIssueMgmt.compositeId.stoItemNo)
                        )
                    )
                    .join(ksdStoIssue).on(
                        ksdStoIssueAplct.compositeId.issueNo.eq(ksdStoIssue.issueNo)
                    )
                    .where(
                        ksdStoIssueAplct.compositeId.issueMgmtInstNo.eq(issueMgmtInstNo),
                        ksdStoIssueAplct.compositeId.issueMgmtInstAcntNo.eq(issueMgmtInstAcntNo),
                        ksdStoIssueAplct.stoItemNo.eq(stoItemNo),
                    )
                    .fetchOne()
    }

    private fun searchCondition(search: String, bookmark: String?): BooleanBuilder {
        val builder = BooleanBuilder()
        if(search != "") {
            builder.and(
                ksdStoIssueAplct.stoItemName.contains(search).or(
                    ksdStoIssueAplct.stoItemNo.contains(search)
                ).or(
                    ksdStoIssue.issueName.contains(search)
                ).or(
                    ksdStoIssueAplct.compositeId.issueMgmtInstNo.contains(search)
                ).or(
                    ksdStoIssueAplct.compositeId.issueMgmtInstAcntNo.contains(search)
                ).or(
                    ksdStoIssueAplct.compositeId.issueNo.contains(search)
                ).or(
                    ksdStoIssueAplct.stoItemName.contains(search)
                )
            )
        }
        bookmark?.let {
            if(it.isNotBlank()) {
                builder.and(ksdStoIssueAplct.compositeId.issueNo.lt(it))
            }
        }
        return builder
    }

}
