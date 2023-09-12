package io.medium.poc.domain.isr.repository.custom.impl

import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.isr.model.dto.IsrStoIssueDto
import io.medium.poc.domain.isr.model.entity.QIsrStoIssue.isrStoIssue
import io.medium.poc.domain.isr.model.entity.QIsrStoIssueAplct.isrStoIssueAplct
import io.medium.poc.domain.isr.model.entity.QIsrStoIssueMgmt.isrStoIssueMgmt
import io.medium.poc.domain.isr.repository.custom.CustomIsrStoIssueRepository
import org.springframework.jdbc.core.JdbcTemplate

class CustomIsrStoIssueRepositoryImpl(
    private val query: JPAQueryFactory,
    private val jdbc: JdbcTemplate,
): CustomIsrStoIssueRepository {

    override fun isrStoIssue(issueNo: String, issueMgmtInstNo: String, issueMgmtInstAcntNo: String): IsrStoIssueDto? {
        return query.select(constructor(IsrStoIssueDto::class.java,
                        isrStoIssue.issueName,
                        isrStoIssue.issueRprsnName,
                        isrStoIssue.issueChargeName,
                        isrStoIssue.issueContactpnt,
                        isrStoIssue.issueEmail,
                        isrStoIssue.issueHomepage,
                        isrStoIssue.issueAddr,
                        isrStoIssue.issueBusiRegNo,
                        isrStoIssue.issueCorpRegNo,
                        isrStoIssue.issueTrdInstNo,
                        isrStoIssue.issueTrdAcntNo,
                        isrStoIssueAplct.compositeId.issueMgmtInstNo,
                        isrStoIssueAplct.compositeId.issueMgmtInstAcntNo,
                        isrStoIssueAplct.issueApprQty,
                        isrStoIssueAplct.stoItemName,
                        isrStoIssueAplct.stoItemNo,
                        isrStoIssueAplct.issueGoodsEvlAmt,
                        isrStoIssueAplct.issuePrice,
                        isrStoIssueAplct.stoBaseAssetType,
                        isrStoIssueAplct.issueGoodsEvlData,
                        isrStoIssueAplct.issueGoodsOwnEvidenceData,
                        isrStoIssueMgmt.issueStatusType,
                    ))
                    .from(isrStoIssue)
                    .join(isrStoIssueAplct).on(
                        isrStoIssue.issueNo.eq(isrStoIssueAplct.compositeId.issueNo)
                    )
                    .leftJoin(isrStoIssueMgmt).on(
                        isrStoIssueAplct.compositeId.issueMgmtInstNo.eq(isrStoIssueMgmt.compositeId.issueMgmtInstNo)
                        .and(
                            isrStoIssueAplct.compositeId.issueMgmtInstAcntNo.eq(isrStoIssueMgmt.compositeId.issueMgmtInstAcntNo)
                        )
                        .and(
                            isrStoIssueAplct.compositeId.issueNo.eq(isrStoIssueMgmt.issueNo)
                        )
                        .and(
                            isrStoIssueAplct.stoItemNo.eq(isrStoIssueMgmt.compositeId.issueMgmtInstNo)
                        )
                    )
                    .where(
                        isrStoIssueAplct.compositeId.issueMgmtInstNo.eq(issueMgmtInstNo),
                        isrStoIssueAplct.compositeId.issueMgmtInstAcntNo.eq(issueMgmtInstAcntNo),
                        isrStoIssueAplct.compositeId.issueNo.eq(issueNo)
                    )
                    .fetchOne()
    }

    override fun retrieveIssueNo(): String {
        val sql = "SELECT funcISR_isrStoIssue_issueNo() FROM dual"
        return jdbc.queryForObject(sql, String::class.java)!!

    }

}
