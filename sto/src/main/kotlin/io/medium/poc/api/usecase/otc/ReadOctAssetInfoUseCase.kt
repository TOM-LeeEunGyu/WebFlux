package io.medium.poc.api.usecase.otc

import io.medium.poc.api.controller.otc.request.query.OctAssetInfoQuery
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.domain.otc.model.dto.OctAssetDto
import io.medium.poc.domain.otc.service.ReadOtcAssetInfoService
import org.springframework.stereotype.Service

@Service
class ReadOctAssetInfoUseCase(
    private val readOtcAssetInfoService: ReadOtcAssetInfoService,
) {
    fun execute(query: OctAssetInfoQuery): ResultResponse<OctAssetDto> {
        val list = with(query) {
            readOtcAssetInfoService.assetInfoDataList(custMgmtInstNo, custMgmtInstAcntNo, recordsCount, bookmark)
        }

        val info = with(query) {
            readOtcAssetInfoService.assetInfo(custMgmtInstNo, custMgmtInstAcntNo)
        }

        val result = list.take((query.recordsCount).toInt())

        val stoItemName = if(result.isNotEmpty()) {
            result[0].stoItemName
        } else {
            null
        }

        val isLast = list.size <= query.recordsCount.toInt()

        val octAssetDto = OctAssetDto(
                depositAmt = info.depositAmt!!,
                trdMrgnAmt = info.trdMrgnAmt!!,
                bookmark = 0,
                last = isLast,
                recordsCount = result.size.toLong(),
                data = result,
        )

        return ResultResponse.of(octAssetDto)
    }

}
