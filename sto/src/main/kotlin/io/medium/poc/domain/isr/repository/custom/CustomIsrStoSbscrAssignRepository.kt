package io.medium.poc.domain.isr.repository.custom

import io.medium.poc.domain.isr.model.dto.IsrInvestorInfoDto
import io.medium.poc.domain.isr.model.dto.IsrInvestorListDto
import io.medium.poc.domain.isr.model.entity.IsrStoSbscrAssign

interface CustomIsrStoSbscrAssignRepository {

    fun getIsrInvestors(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
        recordsCount: Long,
        bookmark: Long
    ): List<IsrInvestorListDto>

    fun getIsrInvestorInfo(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): IsrInvestorInfoDto

    fun batchForIsrStoSbscrAssigns(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): List<IsrStoSbscrAssign>

}
