package io.medium.poc.domain.isr.repository.custom.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections.constructor
import com.querydsl.jpa.impl.JPAQueryFactory
import io.medium.poc.domain.isr.model.dto.IsrStoIssueAplctDto
import io.medium.poc.domain.isr.model.entity.QIsrStoIssue.isrStoIssue
import io.medium.poc.domain.isr.model.entity.QIsrStoIssueAplct.isrStoIssueAplct
import io.medium.poc.domain.isr.model.entity.QIsrStoIssueMgmt.isrStoIssueMgmt
import io.medium.poc.domain.isr.repository.custom.CustomIsrStoIssueAplctRepository
import java.math.BigDecimal

class CustomIsrStoIssueAplctRepositoryImpl(
    private val query: JPAQueryFactory,
): CustomIsrStoIssueAplctRepository {

    override fun isrStoIssueApplications(
        search: String,
        recordsCount: Long,
        bookmark: String?,
    ): List<IsrStoIssueAplctDto> {
        return query.select(constructor(IsrStoIssueAplctDto::class.java,
                        isrStoIssueAplct.compositeId.issueMgmtInstNo,
                        isrStoIssueAplct.compositeId.issueMgmtInstAcntNo,
                        isrStoIssueAplct.compositeId.issueNo,
                        isrStoIssueAplct.stoItemName,
                        isrStoIssueAplct.stoItemNo,
                        isrStoIssueAplct.issueApprQty.`as`("issueProposalQty"),
                        isrStoIssueMgmt.issueApprQty.coalesce(BigDecimal.ZERO).`as`("issueApproveQty"),
                        isrStoIssue.issueName,
                        isrStoIssueMgmt.issueAplctDt,
                        isrStoIssue.lstIssueDt,
                        isrStoIssueMgmt.issueStatusType,
                        isrStoIssueAplct.recYn,
                        isrStoIssueAplct.apprYn,
                        isrStoIssueAplct.sndYn,
                    ))
                    .from(isrStoIssueAplct)
                    .join(isrStoIssue)
                    .on(isrStoIssueAplct.compositeId.issueNo.eq(isrStoIssue.issueNo))
                    .leftJoin(isrStoIssueMgmt).on(
                        isrStoIssueMgmt.compositeId.issueMgmtInstNo.eq(isrStoIssueAplct.compositeId.issueMgmtInstNo)
                        .and(
                            isrStoIssueMgmt.compositeId.issueMgmtInstAcntNo.eq(isrStoIssueAplct.compositeId.issueMgmtInstAcntNo)
                        )
                        .and(
                            isrStoIssueAplct.compositeId.issueNo.eq(isrStoIssueMgmt.issueNo)
                        )
                        .and(
                            isrStoIssueAplct.stoItemNo.eq(isrStoIssueMgmt.compositeId.stoItemNo)
                        )
                    )
                    .where(
                        searchCondition(search, bookmark),
                    )
                    .orderBy(isrStoIssueAplct.compositeId.issueNo.desc())
                    .limit(recordsCount + 1)
                    .fetch()
    }

    private fun searchCondition(search: String, bookmark: String?): BooleanBuilder {
        val builder = BooleanBuilder()
        if(search != "") {
            builder.and(
                isrStoIssueAplct.stoItemName.contains(search)
                    .or(isrStoIssueAplct.stoItemNo.contains(search))
                    .or(isrStoIssue.issueName.contains(search))
                    .or(isrStoIssueAplct.compositeId.issueMgmtInstNo.contains(search))
                    .or(isrStoIssueAplct.compositeId.issueMgmtInstAcntNo.contains(search))
                    .or(isrStoIssueAplct.compositeId.issueNo.contains(search))
                    .or(isrStoIssueAplct.stoItemName.contains(search))
            )
        }
        bookmark?.let {
            if(it.isNotBlank()) {
                builder.and(isrStoIssueAplct.compositeId.issueNo.lt(it))
            }
        }
        return builder
    }

}
