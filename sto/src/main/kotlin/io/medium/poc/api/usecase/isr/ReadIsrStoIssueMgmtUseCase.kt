package io.medium.poc.api.usecase.isr

import io.medium.poc.api.controller.isr.request.query.IsrStoIssueIssuerInfoQuery
import io.medium.poc.api.controller.isr.request.query.IsrStoIssuePubInfoQuery
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.domain.isr.model.dto.IsrPubInfoDto
import io.medium.poc.domain.isr.model.dto.IssuerInfoDto
import io.medium.poc.domain.isr.service.ReadIsrService
import org.springframework.stereotype.Service

@Service
class ReadIsrStoIssueMgmtUseCase(
    private val readIsrService: ReadIsrService,
) {
    fun getIssuerInfo(query: IsrStoIssueIssuerInfoQuery): ResultResponse<IssuerInfoDto> {
        val result = with(query) {
            readIsrService.getIssuerInfo(issueNo)
        }
        return ResultResponse.of(result)

    }

    /**
     * 발행사 게재정보접수_게재정보 조회
     */
    fun getIsrPubInfo(query: IsrStoIssuePubInfoQuery): ResultResponse<IsrPubInfoDto> {
        val result = with(query) {
            readIsrService.getIsrPubInfo(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo, issueNo)
        }
        return ResultResponse.of(result)
    }

}
