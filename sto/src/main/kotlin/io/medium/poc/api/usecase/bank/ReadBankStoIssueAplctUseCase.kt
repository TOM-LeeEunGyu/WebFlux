package io.medium.poc.api.usecase.bank

import io.medium.poc.api.controller.bank.request.query.BankSearchConditionQuery
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.common.utils.lastAt
import io.medium.poc.domain.bank.model.dto.BankStoIssueAplctDto
import io.medium.poc.domain.bank.service.ReadBankService
import org.springframework.stereotype.Service

@Service
class ReadBankStoIssueAplctUseCase(
    private val readBankService: ReadBankService,
) {

    fun execute(query: BankSearchConditionQuery): ResultResponsePagination<BankStoIssueAplctDto> {
        val list = with(query) {
            readBankService.bankStoIssueApplications(searchCondition, recordsCount, bookmark)
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

        val bookmark = if (!isLast) {
            result.lastAt().issueNo
        } else {
            null
        }

        return ResultResponsePagination.of(
            bookmark = bookmark,
            last = isLast,
            recordsCount = result.size.toLong(),
            data = result,
        )

    }

}
