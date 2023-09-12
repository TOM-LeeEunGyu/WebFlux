package io.medium.poc.domain.ksd.repository.custom

import io.medium.poc.domain.ksd.model.dto.KsdPostingInfoDto
import io.medium.poc.domain.ksd.model.dto.KsdStoIssueAplctDto

interface CustomKsdStoIssueAplctRepository {

    fun ksdStoIssueApplications(
        search: String,
        recordsCount: Long,
        bookmark: String?
    ): List<KsdStoIssueAplctDto>

    fun ksdStoIssuePostingInformation(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): KsdPostingInfoDto?

}
