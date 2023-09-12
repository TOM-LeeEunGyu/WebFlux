package io.medium.poc.domain.ksd.repository.custom.impl

import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.ksd.model.dto.KsdStoIssueDto
import io.medium.poc.domain.ksd.model.entity.QKsdStoIssue.ksdStoIssue
import io.medium.poc.domain.ksd.model.entity.QKsdStoIssueAplct.ksdStoIssueAplct
import io.medium.poc.domain.ksd.repository.custom.CustomKsdStoIssueRepository

class CustomKsdStoIssueRepositoryImpl(
    private val query: JPAQueryFactory,
): CustomKsdStoIssueRepository {

    override fun ksdStoIssue(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        issueNo: String
    ): KsdStoIssueDto? {
        return query.select(constructor(KsdStoIssueDto::class.java,
                        ksdStoIssue.issueName,
                        ksdStoIssue.issueRprsnName,
                        ksdStoIssue.issueEmail,
                        ksdStoIssue.issueTrdInstNo,
                        ksdStoIssue.issueChargeName,
                        ksdStoIssue.issueAddr,
                        ksdStoIssue.issueBusiRegNo,
                        ksdStoIssue.issueCorpRegNo,
                        ksdStoIssue.issueContactpnt,
                        ksdStoIssue.issueTrdAcntNo,
                        ksdStoIssueAplct.stoItemName,
                        ksdStoIssueAplct.stoItemNo,
                        ksdStoIssueAplct.issueApprQty,
                        ksdStoIssueAplct.stoBaseAssetType,
                        ksdStoIssueAplct.issueGoodsEvlAmt,
                        ksdStoIssueAplct.issuePrice,
                        ksdStoIssueAplct.apprYn
                    ))
                    .from(ksdStoIssue)
                    .join(ksdStoIssueAplct).on(
                        ksdStoIssue.issueNo.eq(ksdStoIssueAplct.compositeId.issueNo)
                    )
                    .where(
                        ksdStoIssueAplct.compositeId.issueMgmtInstNo.eq(issueMgmtInstNo),
                        ksdStoIssueAplct.compositeId.issueMgmtInstAcntNo.eq(issueMgmtInstAcntNo),
                        ksdStoIssueAplct.compositeId.issueNo.eq(issueNo)
                    )
                    .fetchOne()
    }

}
