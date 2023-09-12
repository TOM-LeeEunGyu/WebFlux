package io.medium.poc.api.usecase.sec

import io.medium.poc.api.controller.sec.request.query.SecAcntValidateSearchQuery
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.common.utils.lastAt
import io.medium.poc.domain.sec.model.dto.SecAccountValidateDto
import io.medium.poc.domain.sec.service.ReadSecService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadSecAccountValidateUseCase(
    private val readSecService: ReadSecService,
) {

    @Transactional
    fun execute(query: SecAcntValidateSearchQuery): ResultResponsePagination<SecAccountValidateDto> {
        val list = with(query) {
            readSecService.getSecAccountValidateList(reqDt, recordsCount, bookmark)
        }

        if(list.isEmpty()) {
            return ResultResponsePagination.of(
                last = true,
                recordsCount = 0,
                data = emptyList(),
            )
        }
        val result = list.take((query.recordsCount).toInt())

        val isLast = list.size <= query.recordsCount.toInt()

        val bookmark = if (!isLast) {
            result.lastAt().compositeId.confirmNo
        } else {
            null
        }

        return ResultResponsePagination.of(
            bookmark = bookmark.toString(),
            last = isLast,
            recordsCount = result.size.toLong(),
            data = result.map(SecAccountValidateDto::toDto),
        )

    }

}
