package io.medium.poc.api.usecase.ksd

import io.medium.poc.api.controller.ksd.request.query.KsdIssueStoSbscrAplctQuery
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.domain.ksd.model.dto.KsdStoSbscrAplctDto
import io.medium.poc.domain.ksd.service.ReadKsdService
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ReadKsdIssueStoSbscrAplctUseCase(
    private val readKsdService: ReadKsdService,
) {

    fun execute(query: KsdIssueStoSbscrAplctQuery): ResultResponse<KsdStoSbscrAplctDto> {
        val list = with(query) {
            readKsdService.ksdStoSbscrApplications(
                issueMgmtInstNo,
                issueMgmtInstAcntNo,
                stoItemNo,
                recordsCount,
                bookmark
            )
        }

        val info = with(query) {
            readKsdService.ksdStoSbscrAplctInfo(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }

        val result = list.take((query.recordsCount).toInt())
        val sbscrAmt = if(result.isNotEmpty()) {
            result[0].issueAmt
        } else {
            BigDecimal.ZERO
        }

        val isLast = list.size <= query.recordsCount.toInt()

        val ksdStoSbscrAplctDto = KsdStoSbscrAplctDto(
            issueSbscrPersonCnt = info.issueSbscrPersonCnt,
            sbscrAmt = sbscrAmt,
            issueSbscrMarginAmt = info.issueSbscrMarginAmt,
            sbscrReturnAmt = info.sbscrReturnAmt,
            bookmark = 0,
            last = isLast,
            recordsCount = result.size.toLong(),
            data = result,
        )

        return ResultResponse.of(ksdStoSbscrAplctDto)
    }

}
