package io.medium.poc.api.usecase.ksd

import io.medium.poc.api.controller.ksd.request.query.KsdSearchConditionQuery
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.common.utils.lastAt
import io.medium.poc.domain.ksd.model.dto.KsdStoIssueAplctDto
import io.medium.poc.domain.ksd.service.ReadKsdService
import org.springframework.stereotype.Service

@Service
class ReadKsdIssueAplctUseCase(
    private val readKsdService: ReadKsdService,
) {

    fun execute(query: KsdSearchConditionQuery): ResultResponsePagination<KsdStoIssueAplctDto> {
        val list = with(query) {
            readKsdService.ksdStoIssueApplications(searchCondition, recordsCount, bookmark)
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
