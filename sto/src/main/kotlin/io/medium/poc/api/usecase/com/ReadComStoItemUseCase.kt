package io.medium.poc.api.usecase.com

import io.medium.poc.api.controller.com.model.request.ComStoItemQuery
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.common.utils.lastAt
import io.medium.poc.domain.com.model.dto.ComStoItemDto
import io.medium.poc.domain.com.service.ReadComStoItemService
import org.springframework.stereotype.Service

@Service
class ReadComStoItemUseCase(
    private val readComStoItemService: ReadComStoItemService,
) {

    fun execute(query: ComStoItemQuery): ResultResponsePagination<ComStoItemDto> {
        val list = with(query) {
            readComStoItemService.comStoItems(stoItemNo, stoBaseAssetType(), trdStartYn(), recordsCount, bookmark)
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
        val bookmark = if(!isLast) {
            result.lastAt().stoItemNo
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
