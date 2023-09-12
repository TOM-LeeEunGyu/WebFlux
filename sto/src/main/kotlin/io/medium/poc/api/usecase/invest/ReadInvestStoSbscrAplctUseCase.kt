package io.medium.poc.api.usecase.invest

import io.medium.poc.api.controller.invest.request.query.InvestStoSbscrAplctQuery
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.domain.invest.model.dto.InvestStoSbscrAplctDto
import io.medium.poc.domain.invest.service.ReadInvestService
import org.springframework.stereotype.Service

@Service
class ReadInvestStoSbscrAplctUseCase(
    private val readInvestService: ReadInvestService,
) {

    fun execute(query: InvestStoSbscrAplctQuery): ResultResponse<InvestStoSbscrAplctDto> {
        val list = with(query) {
            readInvestService.investStoSbscrApplication(stoItemNo, custMgmtInstNo, custMgmtInstAcntNo)
        }
        return ResultResponse.of(list)
    }

}
