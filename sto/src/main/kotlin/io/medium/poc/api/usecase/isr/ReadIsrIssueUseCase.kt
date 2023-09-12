package io.medium.poc.api.usecase.isr

import io.medium.poc.api.controller.isr.request.query.IsrStoIssueRegistrationQuery
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.domain.isr.model.dto.IsrStoIssueDto
import io.medium.poc.domain.isr.service.ReadIsrService
import org.springframework.stereotype.Service

@Service
class ReadIsrIssueUseCase(
    private val readIsrService: ReadIsrService,
) {

    fun execute(query: IsrStoIssueRegistrationQuery): ResultResponse<IsrStoIssueDto> {
        val result = with(query) {
            readIsrService.isrStoIssue(issueNo, issueMgmtInstNo, issueMgmtInstAcntNo)
        }
        return ResultResponse.of(result)
    }

}
