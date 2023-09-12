package io.medium.poc.domain.isr.repository.custom

import io.medium.poc.domain.isr.model.dto.IsrStoIssueAplctDto

interface CustomIsrStoIssueAplctRepository {

    fun isrStoIssueApplications(
        search:String,
        recordsCount: Long,
        bookmark: String?,
    ): List<IsrStoIssueAplctDto>

}
