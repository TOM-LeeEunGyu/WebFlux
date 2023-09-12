package io.medium.poc.domain.isr.repository.custom

import io.medium.poc.domain.isr.model.dto.IsrPubInfoDto

interface CustomIsrStoIssueMgmtRepository {

    fun findIsrPubInfo(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
        issueNo:String
    ): IsrPubInfoDto?

}
