package io.medium.poc.domain.invest.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.common.code.IssueStatusType
import io.medium.poc.common.utils.notFoundEntity
import io.medium.poc.domain.com.model.entity.QComInvestor.comInvestor
import io.medium.poc.domain.com.model.entity.QComStoItem.comStoItem
import io.medium.poc.domain.invest.model.dto.InvestStoIssueMgmtDto
import io.medium.poc.domain.invest.model.dto.InvestStoSbscrAplctDto
import io.medium.poc.domain.isr.model.entity.QIsrStoIssue.isrStoIssue
import io.medium.poc.domain.isr.model.entity.QIsrStoIssueMgmt.isrStoIssueMgmt
import io.medium.poc.domain.isr.model.entity.QIsrStoSbscrAplct.isrStoSbscrAplct
import org.springframework.stereotype.Repository

@Repository
class CustomInvestRepository(
    private val query: JPAQueryFactory,
) {

    fun investStoIssueManagements(
        search: String,
        recordsCount: Long,
        bookmark:Long
    ): List<InvestStoIssueMgmtDto> {
        return query.select(constructor(InvestStoIssueMgmtDto::class.java,
                        isrStoIssueMgmt.compositeId.stoItemNo,
                        comStoItem.stoItemName,
                        isrStoIssue.issueName,
                        isrStoIssueMgmt.issueAmt,
                        isrStoIssueMgmt.issueSbscrAplctStartDt,
                        isrStoIssueMgmt.issueSbscrAplctEndDt,
                        isrStoIssueMgmt.issueSbscrAssignDt,
                        isrStoIssueMgmt.issueStatusType,
                        isrStoIssueMgmt.compositeId.issueMgmtInstAcntNo,
                        isrStoIssueMgmt.compositeId.issueMgmtInstNo
                    ))
                    .from(isrStoIssueMgmt)
                    .join(isrStoIssue).on(
                        isrStoIssueMgmt.issueNo.eq(isrStoIssue.issueNo)
                    )
                    .join(comStoItem).on(
                        isrStoIssueMgmt.compositeId.stoItemNo.eq(comStoItem.stoItemNo),
                    )
                    .where(
                        isrStoIssueMgmt.issueStatusType.`in`(
                            IssueStatusType.ONGOING_SUB,
                            IssueStatusType.SUB_CLOSED,
                            IssueStatusType.OFFER_APPROVAL,
                            IssueStatusType.ASSIGN_APPROVAL,
                            IssueStatusType.SUB_CLOSED,
                        ),
                        searchCondition(search, bookmark)
                    )
                    .orderBy(isrStoIssueMgmt.issueAplctDt.desc(), isrStoIssueMgmt.issueApprDt.desc())
                    .limit(recordsCount + 1)
                    .fetch()
    }

    private fun searchCondition(search: String, bookmark: Long): BooleanBuilder {
        val builder = BooleanBuilder()
        if(search != "") {
            builder.and(comStoItem.stoItemName.contains(search)
                   .or(isrStoIssueMgmt.compositeId.stoItemNo.contains(search))
                   .or(isrStoIssue.issueName.contains(search))
                   .or(isrStoIssueMgmt.compositeId.issueMgmtInstNo.contains(search))
                   .or(isrStoIssueMgmt.compositeId.issueMgmtInstAcntNo.contains(search))
                   .or(isrStoIssueMgmt.issueNo.contains(search))
            )
        }
        return builder
    }

    fun investStoSbscrApplication(
        stoItemNo: String,
        custMgmtInstNo: String,
        custMgmtInstAcntNo: String,
    ): InvestStoSbscrAplctDto {
        val errorMessage = "STO 종목번호[$stoItemNo], 고객관리기관번호[$custMgmtInstNo], 고객관리기관계좌번호[$custMgmtInstAcntNo]로 조회된 정보가 없습니다."

        return query.select(constructor(InvestStoSbscrAplctDto::class.java,
                        isrStoSbscrAplct.compositeId.stoItemNo,
                        comStoItem.stoItemName,
                        isrStoSbscrAplct.sbscrName,
                        isrStoSbscrAplct.invstId,
                        isrStoSbscrAplct.investorIdType,
                        isrStoSbscrAplct.compositeId.custMgmtInstNo,
                        isrStoSbscrAplct.compositeId.issueMgmtInstAcntNo,
                        isrStoSbscrAplct.issueSbscrAplctQty,
                        isrStoSbscrAplct.issueSbscrMarginAmt,
                    ))
                    .from(isrStoSbscrAplct)
                    .leftJoin(comStoItem).on(
                        isrStoSbscrAplct.compositeId.stoItemNo.eq(comStoItem.stoItemNo)
                    )
                    .leftJoin(comInvestor).on(
                        isrStoSbscrAplct.compositeId.custMgmtInstNo.eq(isrStoSbscrAplct.compositeId.custMgmtInstNo)
                        .and(
                            isrStoSbscrAplct.compositeId.issueMgmtInstAcntNo.eq(isrStoSbscrAplct.compositeId.issueMgmtInstAcntNo)
                        )
                        .and(
                            isrStoSbscrAplct.invstId.eq(comInvestor.investId)
                        )
                    )
                    .where(
                        isrStoSbscrAplct.compositeId.stoItemNo.eq(stoItemNo),
                        isrStoSbscrAplct.compositeId.custMgmtInstNo.eq(custMgmtInstNo),
                        isrStoSbscrAplct.compositeId.custMgmtInstAcntNo.eq(custMgmtInstAcntNo),
                    )
                    .fetchOne() ?: notFoundEntity(errorMessage)
    }

}
