package io.medium.poc.domain.ksd.repository

import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.ksd.model.entity.KsdStoIssueAplct
import io.medium.poc.domain.ksd.model.entity.KsdStoIssueAplctMultiKeys
import io.medium.poc.domain.ksd.repository.custom.CustomKsdStoIssueAplctRepository

/**
 * 예탁원 STO 청약신청내역 repository
 */
interface KsdStoIssueAplctRepository
    : BaseRepository<KsdStoIssueAplct, KsdStoIssueAplctMultiKeys>, CustomKsdStoIssueAplctRepository
{
    fun findBySndYnAndIssueApprDtIsNotNull(sendYn: YesOrNo): List<KsdStoIssueAplct>

}
