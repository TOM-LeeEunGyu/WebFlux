package io.medium.poc.api.usecase.isr

import io.medium.poc.api.controller.isr.request.query.IsrSearchConditionQuery
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.common.utils.lastAt
import io.medium.poc.domain.isr.model.dto.IsrStoIssueAplctDto
import io.medium.poc.domain.isr.service.ReadIsrService
import org.springframework.stereotype.Service

@Service
class ReadIsrIssueAplctUseCase(
    private val readIsrService: ReadIsrService,
) {
    fun execute(query: IsrSearchConditionQuery): ResultResponsePagination<IsrStoIssueAplctDto> {
        val list = readIsrService.isrStoIssueApplications(query.searchCondition, query.recordsCount, query.bookmark)
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
