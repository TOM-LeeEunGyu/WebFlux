package io.medium.poc.api.usecase.ksd

import io.medium.poc.api.controller.ksd.request.query.KsdPostingInfoQuery
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.domain.ksd.model.dto.KsdPostingInfoDto
import io.medium.poc.domain.ksd.service.ReadKsdService
import org.springframework.stereotype.Service

@Service
class ReadKsdIssuePostingInfoUseCase(
    private val readKsdService: ReadKsdService,
) {

    fun execute(query: KsdPostingInfoQuery): ResultResponse<KsdPostingInfoDto> {
        val result = with(query) {
            readKsdService.ksdStoIssuePostingInformation(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }
        return ResultResponse.of(result)
    }

}
