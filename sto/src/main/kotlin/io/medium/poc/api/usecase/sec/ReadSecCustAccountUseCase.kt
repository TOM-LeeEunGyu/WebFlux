package io.medium.poc.api.usecase.sec

import io.medium.poc.api.controller.sec.request.query.SecCustAccountQuery
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.domain.sec.model.dto.AssetInfoDto
import io.medium.poc.domain.sec.service.ReadSecService
import org.springframework.stereotype.Service

@Service
class ReadSecCustAccountUseCase(
    private val readSecService: ReadSecService,
) {
    fun execute(query: SecCustAccountQuery): ResultResponsePagination<AssetInfoDto> {
        val list = with(query) {
            readSecService.getAssetInformation(custMgmtInstNo, custMgmtInstAcntNo, recordsCount, bookmark)
        }
        if(list.isEmpty()) {
            return ResultResponsePagination.of(
                last = true,
                recordsCount = 0,
                data = list,
            )
        }
        val result = list.take((query.recordsCount).toInt())

        val isLast = list.size <= query.recordsCount.toInt()

        return ResultResponsePagination.of(
            last = isLast,
            recordsCount = result.size.toLong(),
            data = result,
        )

    }

}
