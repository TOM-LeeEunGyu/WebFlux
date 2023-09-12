package io.medium.poc.api.usecase.ksd

import io.medium.poc.api.controller.ksd.request.query.KsdIssueStoAplctQuery
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.domain.ksd.model.dto.KsdStoIssueDto
import io.medium.poc.domain.ksd.service.ReadKsdService
import org.springframework.stereotype.Service

@Service
class ReadKsdIssueUseCase(
    private val readKsdService: ReadKsdService,
) {

    fun execute(query: KsdIssueStoAplctQuery): ResultResponse<KsdStoIssueDto> {
        val result = with(query) {
            readKsdService.fetchKsdStoIssue(issueMgmtInstNo, issueMgmtInstAcntNo, issueNo)
        }
        return ResultResponse.of(result)
    }

}
