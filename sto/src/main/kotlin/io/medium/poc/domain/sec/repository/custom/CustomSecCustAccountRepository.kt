package io.medium.poc.domain.sec.repository.custom

import io.medium.poc.domain.sec.model.dto.AssetInfoDto
import io.medium.poc.domain.sec.model.entity.SecAccountValidate
import java.time.LocalDate

interface CustomSecCustAccountRepository {

    fun getAssetInformation(
        custMgmtInstNo: String,
        custMgmtInstAcntNo: String,
        recordsCount: Long,
        bookmark:Long
    ): List<AssetInfoDto>

    fun getSecAccountValidateList(reqDt: LocalDate, recordsCount: Long, bookmark: Long?): List<SecAccountValidate>
}
