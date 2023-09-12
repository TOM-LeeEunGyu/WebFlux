package io.medium.poc.domain.ksd.repository

import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.ksd.model.entity.KsdStoIssue
import io.medium.poc.domain.ksd.repository.custom.CustomKsdStoIssueRepository

/**
 * 예탁원 STO 발행인정보 repository
 */
interface KsdStoIssueRepository: BaseRepository<KsdStoIssue, String>, CustomKsdStoIssueRepository {
    fun findBySndYn(sndYn: YesOrNo): List<KsdStoIssue>
}
