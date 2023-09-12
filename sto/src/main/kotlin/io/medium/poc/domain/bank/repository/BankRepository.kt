package io.medium.poc.domain.bank.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.common.code.IssueStatusType
import io.medium.poc.domain.bank.model.dto.BankSettlementIssueInfoDto
import io.medium.poc.domain.bank.model.dto.BankStoIssueAplctDto
import io.medium.poc.domain.com.model.entity.QComStoItem.comStoItem
import io.medium.poc.domain.isr.model.entity.QIsrStoIssue.isrStoIssue
import io.medium.poc.domain.isr.model.entity.QIsrStoIssueAplct.isrStoIssueAplct
import io.medium.poc.domain.isr.model.entity.QIsrStoSbscrAplct.isrStoSbscrAplct
import io.medium.poc.domain.isr.model.entity.QIsrStoSbscrAssign.isrStoSbscrAssign
import io.medium.poc.domain.ksd.model.entity.QKsdStoIssue.ksdStoIssue
import io.medium.poc.domain.ksd.model.entity.QKsdStoIssueAplct.ksdStoIssueAplct
import io.medium.poc.domain.ksd.model.entity.QKsdStoIssueMgmt.ksdStoIssueMgmt
import org.springframework.stereotype.Repository
import java.math.BigDecimal

/**
 * 은행 repository
 */
@Repository
class BankRepository(
    private val query: JPAQueryFactory,
) {

    /**
     * 은행발행대금정산처리 조회
     */
    fun bankSettlementIssueInformation(stoItemNo: String): BankSettlementIssueInfoDto {
        return query.select(constructor(BankSettlementIssueInfoDto::class.java,
                        comStoItem.stoItemName,
                        comStoItem.stoItemNo,
                        isrStoIssue.issueName,
                        isrStoSbscrAplct.issueSbscrMarginAmt.sum().coalesce(BigDecimal.ZERO),
                        isrStoSbscrAssign.sbscrAmt.sum().coalesce(BigDecimal.ZERO),
                        isrStoSbscrAssign.sbscrReturnAmt.sum().coalesce(BigDecimal.ZERO)
                    ))
                    .from(isrStoSbscrAplct)
                    .join(isrStoSbscrAssign).on(
                        isrStoSbscrAssign.compositeId.issueMgmtInstNo.eq(isrStoSbscrAplct.compositeId.issueMgmtInstNo)
                        .and(
                            isrStoSbscrAssign.compositeId.issueMgmtInstAcntNo.eq(isrStoSbscrAplct.compositeId.issueMgmtInstAcntNo)
                        )
                        .and(
                            isrStoSbscrAssign.compositeId.custMgmtInstNo.eq(isrStoSbscrAplct.compositeId.custMgmtInstNo)
                        )
                        .and(
                            isrStoSbscrAssign.compositeId.custMgmtInstAcntNo.eq(isrStoSbscrAplct.compositeId.custMgmtInstAcntNo)
                        )
                        .and(
                            isrStoSbscrAssign.compositeId.stoItemNo.eq(isrStoSbscrAplct.compositeId.stoItemNo)
                        )
                    )
                    .join(comStoItem).on(isrStoSbscrAplct.compositeId.stoItemNo.eq(comStoItem.stoItemNo))
                    .join(isrStoIssueAplct).on(comStoItem.stoItemNo.eq(isrStoIssueAplct.stoItemNo))
                    .join(isrStoIssue).on(isrStoIssueAplct.compositeId.issueNo.eq(isrStoIssue.issueNo))
                    .where(
                        isrStoSbscrAplct.compositeId.stoItemNo.eq(stoItemNo)
                    )
                    .fetchOne()!!
    }

    /**
     * 은행 토큰증권 목록조회
     */
    fun bankStoIssueApplications(
        search: String,
        recordsCount: Long,
        bookmark: String?
    ): List<BankStoIssueAplctDto> {
        return query.select(constructor(BankStoIssueAplctDto::class.java,
                        ksdStoIssueAplct.compositeId.issueNo,
                        ksdStoIssueAplct.stoItemName,
                        ksdStoIssueAplct.stoItemNo,
                        ksdStoIssueAplct.issueApprQty.`as`("issueProposalQty"),
                        ksdStoIssueMgmt.issueApprQty.coalesce(BigDecimal.ZERO).`as`("issueApproveQty"),
                        ksdStoIssue.issueName,
                        ksdStoIssueAplct.issueAplctDt,
                        ksdStoIssue.lstIssueDt,
                        ksdStoIssueMgmt.issueStatusType
                    ))
                    .from(ksdStoIssueAplct)
                    .join(ksdStoIssue).on(ksdStoIssueAplct.compositeId.issueNo.eq(ksdStoIssue.issueNo))
                    .leftJoin(ksdStoIssueMgmt).on(
                        ksdStoIssueAplct.compositeId.issueMgmtInstNo.eq(
                            ksdStoIssueMgmt.compositeId.issueMgmtInstNo
                        )
                        .and(
                            ksdStoIssueAplct.compositeId.issueMgmtInstAcntNo.eq(
                                ksdStoIssueMgmt.compositeId.issueMgmtInstAcntNo
                            )
                        )
                    )
                    .where(
                        ksdStoIssueMgmt.issueStatusType.`in`(
                            IssueStatusType.ONGOING_SUB,
                            IssueStatusType.SUB_CLOSED,
                            IssueStatusType.OFFER_APPROVAL,
                            IssueStatusType.ASSIGN_APPROVAL,
                            IssueStatusType.SUB_CLOSED,
                        ),
                        searchCondition(search, bookmark)
                    )
                    .orderBy(ksdStoIssueAplct.compositeId.issueNo.desc())
                    .limit(recordsCount + 1)
                    .fetch()
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
