package io.medium.poc.api.usecase.invest

import io.medium.poc.api.controller.invest.request.query.InvestSearchConditionQuery
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.domain.invest.model.dto.InvestStoIssueMgmtDto
import io.medium.poc.domain.invest.service.ReadInvestService
import org.springframework.stereotype.Service

@Service
class ReadInvestStoIssueMgmtUseCase(
    private val readInvestService: ReadInvestService,
) {

    fun execute(query: InvestSearchConditionQuery): ResultResponsePagination<InvestStoIssueMgmtDto> {
        val list = with(query) {
            readInvestService.investStoIssueManagements(searchCondition, recordsCount, bookmark)
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
