package io.medium.poc.domain.isr.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.isr.model.dto.IssuerInfoDto
import io.medium.poc.domain.isr.model.entity.IsrStoIssue
import io.medium.poc.domain.isr.repository.custom.CustomIsrStoIssueRepository

interface IsrStoIssueRepository : BaseRepository<IsrStoIssue, String>, CustomIsrStoIssueRepository {
    fun findByIssueNo(issueNo:String): IssuerInfoDto?
}
