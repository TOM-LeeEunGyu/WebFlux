package io.medium.poc.api.usecase.isr

import io.medium.poc.api.controller.isr.request.query.IsrInvestorInfoQuery
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.domain.isr.model.dto.IsrInvestorDto
import io.medium.poc.domain.isr.service.ReadIsrService
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ReadIsrStoSbscrAssignUseCase(
    private val readIsrService: ReadIsrService,
) {
    fun execute(query: IsrInvestorInfoQuery): ResultResponse<IsrInvestorDto> {
        val list = with(query) {
            readIsrService.getIsrInvestors(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo, recordsCount, bookmark)
        }

        val info = with(query) {
            readIsrService.getIsrInvestorInfo(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }

        val result = list.take((query.recordsCount).toInt())
        val sbscrAmt = if(result.isNotEmpty()) {
            result[0].issueAmt
        } else {
            BigDecimal.ZERO
        }

        val stoItemName = if(result.isNotEmpty()) {
            result[0].stoItemName
        } else {
            null
        }

        val isLast = list.size <= query.recordsCount.toInt()

        val isrInvestorDto = IsrInvestorDto(
            issueMgmtInstNo = query.issueMgmtInstNo,
            issueMgmtInstAcntNo = query.issueMgmtInstAcntNo,
            stoItemNo = query.stoItemNo,
            stoItemName = stoItemName,
            issueSbscrPersonCnt = info.issueSbscrPersonCnt,
            sbscrAmt = sbscrAmt,
            issueSbscrMarginAmt = info.issueSbscrMarginAmt,
            sbscrReturnAmt = info.sbscrReturnAmt,
            bookmark = 0,
            last = isLast,
            recordsCount = result.size.toLong(),
            data = result,
        )

        return ResultResponse.of(isrInvestorDto)
    }

}
