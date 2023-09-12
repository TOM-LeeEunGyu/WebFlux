package io.medium.poc.domain.isr.repository.custom

import io.medium.poc.domain.isr.model.dto.IsrStoIssueDto

interface CustomIsrStoIssueRepository {

    fun isrStoIssue(
        issueNo: String,
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
    ): IsrStoIssueDto?

    fun retrieveIssueNo(): String

}
