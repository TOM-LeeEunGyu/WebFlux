package io.medium.poc.domain.ksd.repository.custom

import io.medium.poc.domain.ksd.model.dto.KsdStoIssueDto

interface CustomKsdStoIssueRepository {

    fun ksdStoIssue(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        issueNo: String,
    ): KsdStoIssueDto?

}
