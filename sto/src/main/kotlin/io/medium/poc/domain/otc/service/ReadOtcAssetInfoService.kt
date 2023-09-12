package io.medium.poc.domain.otc.service

import io.medium.poc.domain.otc.model.dto.OctAssetInfoDto
import io.medium.poc.domain.otc.model.dto.OctAssetInfoListDto
import io.medium.poc.domain.otc.repository.OtcStoOrderBookRepository
import org.springframework.stereotype.Service

/**
 * 장외거래중개업자_고객계좌잔고_보유자산정보조회에 사용된다
 */
@Service
class ReadOtcAssetInfoService(
    private val otcStoOrderBookRepository: OtcStoOrderBookRepository,
) {
    fun assetInfo(custMgmtInstNo:String, custMgmtInstAcntNo:String): OctAssetInfoDto {
        return otcStoOrderBookRepository.assetInfo(custMgmtInstNo, custMgmtInstAcntNo)
    }

    fun assetInfoDataList(
        custMgmtInstNo:String,
        custMgmtInstAcntNo:String,
        recordsCount: Long,
        bookmark:Long?
    ): List<OctAssetInfoListDto> {
        return otcStoOrderBookRepository.assetInfoDataList(custMgmtInstNo, custMgmtInstAcntNo, recordsCount, bookmark)
    }

}
