package io.medium.poc.domain.ksd.repository.custom

import io.medium.poc.domain.ksd.model.dto.KsdStoSbscrAplctInfoDto
import io.medium.poc.domain.ksd.model.dto.KsdStoSbscrAplctListDto

interface CustomKsdStoSbscrAplctRepository {

    fun ksdStoSbscrApplications(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
        recordsCount: Long,
        bookmark: Long
    ): List<KsdStoSbscrAplctListDto>

    fun ksdStoSbscrAplctInfo(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): KsdStoSbscrAplctInfoDto

}
