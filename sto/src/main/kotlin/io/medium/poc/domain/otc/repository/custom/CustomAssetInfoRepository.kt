package io.medium.poc.domain.otc.repository.custom

import io.medium.poc.domain.otc.model.dto.OctAssetInfoDto
import io.medium.poc.domain.otc.model.dto.OctAssetInfoListDto

interface CustomAssetInfoRepository {

    fun assetInfo(custMgmtInstNo:String, custMgmtInstAcntNo:String): OctAssetInfoDto

    fun assetInfoDataList(
        custMgmtInstNo:String,
        custMgmtInstAcntNo:String,
        recordsCount: Long,
        bookmark:Long?
    ): List<OctAssetInfoListDto>

}
