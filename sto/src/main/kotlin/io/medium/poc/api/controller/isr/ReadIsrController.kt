package io.medium.poc.api.controller.isr

import io.medium.poc.api.controller.isr.request.query.*
import io.medium.poc.api.usecase.isr.ReadIsrIssueAplctUseCase
import io.medium.poc.api.usecase.isr.ReadIsrIssueUseCase
import io.medium.poc.api.usecase.isr.ReadIsrStoIssueMgmtUseCase
import io.medium.poc.api.usecase.isr.ReadIsrStoSbscrAssignUseCase
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.domain.isr.model.dto.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 발행사 read 컨트롤러
 */
@RestController
@RequestMapping("/api/poc/isr")
@Tag(name = "발행사 조회용 API", description = "발행사 조회 API 를 제공한다.")
class ReadIsrController(
    private val readIsrIssueAplctUseCase: ReadIsrIssueAplctUseCase,
    private val readIsrIssueUseCase: ReadIsrIssueUseCase,
    private val readIsrStoIssueMgmtUseCase: ReadIsrStoIssueMgmtUseCase,
    private val readIsrStoSbscrAssignUseCase: ReadIsrStoSbscrAssignUseCase,
) {

    /** =================== 발행사 공모 정보 endpoint =============== */

    /**
     * 발행사 토큰증권목록 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getIsrStoIssueAplctList")
    @Operation(
        summary = "발행사 토큰증권목록 조회 API",
        description = "발행사 토큰증권목록 조회 정보를 가져온다. 검색 조건을 빈값으로 보내면 전체 리스트를 가져온다."
    )
    fun isrStoIssueApplications(
        @RequestBody @Valid query: IsrSearchConditionQuery
    ): ResultResponsePagination<IsrStoIssueAplctDto> {
        return readIsrIssueAplctUseCase.execute(query)
    }

    /**
     * 발행사 공모신청승인 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getIsrStoIssue")
    @Operation(
        summary = "발행사 공모신청승인 신청 조회 API",
        description = "발행사 공모신청승인 신청 정보를 가져온다."
    )
    fun isrStoIssue(
        @RequestBody @Valid query: IsrStoIssueRegistrationQuery
    ): ResultResponse<IsrStoIssueDto> {
        return readIsrIssueUseCase.execute(query)
    }

    /**
     * 발행사 게재정보접수_발행인정보조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getIssuerInfo")
    @Operation(
        summary = "발행사 게재정보접수_발행인정보조회 API",
        description = "발행사 게재정보접수_발행인정보조회 정보를 가져온다."
    )
    fun getIssuerInfo(
        @RequestBody @Valid query: IsrStoIssueIssuerInfoQuery
    ): ResultResponse<IssuerInfoDto> {
        return readIsrStoIssueMgmtUseCase.getIssuerInfo(query)
    }

    /**
     * 발행사 게재정보접수_게재정보조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getIsrPubInfo")
    @Operation(
        summary = "발행사 게재정보접수_게재정보조회 API",
        description = "발행사 게재정보접수_게재정보조회 정보를 가져온다."
    )
    fun getIsrPubInfo(
        @RequestBody @Valid query: IsrStoIssuePubInfoQuery
    ): ResultResponse<IsrPubInfoDto> {
        return readIsrStoIssueMgmtUseCase.getIsrPubInfo(query)
    }

    /**
     * 발행사 투자자배정신청_투자자배정신청정보조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getIsrInvestorInfo")
    @Operation(
            summary = "발행사 투자자배정신청_투자자배정신청정보조회 API",
            description = "발행사 투자자배정신청_투자자 배정 신청 정보를 가져온다. 검색 조건을 빈값으로 보내면 전체 리스트를 가져온다."
    )
    fun getIsrInvestorInfo(
            @RequestBody @Valid query: IsrInvestorInfoQuery
    ): ResultResponse<IsrInvestorDto> {
        return readIsrStoSbscrAssignUseCase.execute(query)
    }

}
