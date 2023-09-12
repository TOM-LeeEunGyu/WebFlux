package io.medium.poc.domain.isr.repository.custom.impl

import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.isr.model.dto.IsrPubInfoDto
import io.medium.poc.domain.isr.model.entity.QIsrStoIssue.isrStoIssue
import io.medium.poc.domain.isr.model.entity.QIsrStoIssueAplct.isrStoIssueAplct
import io.medium.poc.domain.isr.model.entity.QIsrStoIssueMgmt.isrStoIssueMgmt
import io.medium.poc.domain.isr.repository.custom.CustomIsrStoIssueMgmtRepository

class CustomIsrStoIssueMgmtRepositoryImpl(
    private val query: JPAQueryFactory,
): CustomIsrStoIssueMgmtRepository {

    /** 발행사게재정보접수_게재정보조회 */
    override fun findIsrPubInfo(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
        issueNo: String
    ): IsrPubInfoDto? {
        return query.select(constructor(IsrPubInfoDto::class.java,
                        isrStoIssueAplct.stoItemName,
                        isrStoIssueAplct.stoItemNo,
                        isrStoIssueAplct.issueGoodsEvlAmt,
                        isrStoIssueAplct.issueApprQty,
                        isrStoIssueAplct.stoBaseAssetType,
                        isrStoIssueMgmt.issueAmt,
                        isrStoIssueMgmt.issueSbscrAplctStartDt,
                        isrStoIssueMgmt.issueSbscrAplctEndDt,
                        isrStoIssueMgmt.issueSbscrAssignDt,
                        isrStoIssueMgmt.issueDt,
                        isrStoIssueMgmt.bankInstNo,
                        isrStoIssueMgmt.bankAcntNo,
                        isrStoIssue.issueName,
                        isrStoIssue.issueAddr,
                        isrStoIssue.issueEmail,
                        isrStoIssue.issueRprsnName,
                        isrStoIssue.issueHomepage,
                        isrStoIssueMgmt.businessProfileData,
                        isrStoIssueMgmt.prospectusData,
                        isrStoIssueMgmt.sndYn,
                        isrStoIssueMgmt.recYn,
                        isrStoIssueMgmt.apprYn
                    ))
                    .from(isrStoIssueMgmt)
                    .join(isrStoIssueAplct).on(
                        isrStoIssueMgmt.compositeId.issueMgmtInstNo.eq(isrStoIssueAplct.compositeId.issueMgmtInstNo)
                        .and(
                            isrStoIssueMgmt.compositeId.issueMgmtInstAcntNo.eq(isrStoIssueAplct.compositeId.issueMgmtInstAcntNo)
                        )
                        .and(
                            isrStoIssueMgmt.compositeId.stoItemNo.eq(isrStoIssueAplct.stoItemNo)
                        )
                    )
                    .join(isrStoIssue).on(
                        isrStoIssueAplct.compositeId.issueNo.eq(isrStoIssue.issueNo)
                    )
                    .where(
                        isrStoIssueMgmt.compositeId.issueMgmtInstNo.eq(issueMgmtInstNo),
                        isrStoIssueMgmt.compositeId.issueMgmtInstAcntNo.eq(issueMgmtInstAcntNo),
                        isrStoIssueMgmt.compositeId.stoItemNo.eq(stoItemNo),
                        isrStoIssueMgmt.issueNo.eq(issueNo)
                    )
                    .fetchOne()
    }

}
