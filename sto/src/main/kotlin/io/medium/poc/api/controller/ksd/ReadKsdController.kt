package io.medium.poc.api.controller.ksd

import io.medium.poc.api.controller.ksd.request.query.KsdIssueStoAplctQuery
import io.medium.poc.api.controller.ksd.request.query.KsdIssueStoSbscrAplctQuery
import io.medium.poc.api.controller.ksd.request.query.KsdPostingInfoQuery
import io.medium.poc.api.controller.ksd.request.query.KsdSearchConditionQuery
import io.medium.poc.api.usecase.ksd.ReadKsdIssueAplctUseCase
import io.medium.poc.api.usecase.ksd.ReadKsdIssuePostingInfoUseCase
import io.medium.poc.api.usecase.ksd.ReadKsdIssueStoSbscrAplctUseCase
import io.medium.poc.api.usecase.ksd.ReadKsdIssueUseCase
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.domain.ksd.model.dto.KsdPostingInfoDto
import io.medium.poc.domain.ksd.model.dto.KsdStoIssueAplctDto
import io.medium.poc.domain.ksd.model.dto.KsdStoIssueDto
import io.medium.poc.domain.ksd.model.dto.KsdStoSbscrAplctDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 예탁원 read 컨트롤러
 */
@RestController
@RequestMapping("/api/poc/ksd")
@Tag(name = "예탁원 조회용 API", description = "예탁원 조회 API 를 제공한다.")
class ReadKsdController(
    private val readKsdIssueAplctUseCase: ReadKsdIssueAplctUseCase,
    private val readKsdIssueUseCase: ReadKsdIssueUseCase,
    private val readKsdIssuePostingInfoUseCase: ReadKsdIssuePostingInfoUseCase,
    private val readKsdIssueStoSbscrAplctUseCase: ReadKsdIssueStoSbscrAplctUseCase,
) {

    /** =================== 예탁원 공모 정보 endpoint =============== */

    /**
     * 예탁원 STO 청약신청내역
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getKsdStoIssueAplctList")
    @Operation(
        summary = "예탁원 토큰증권목록 조회 API",
        description = "예탁원 토큰증권목록 조회 정보를 가져온다. 검색 조건을 빈값으로 보내면 전체 리스트를 가져온다."
    )
    fun ksdStoIssueApplications(
        @RequestBody @Valid query: KsdSearchConditionQuery
    ): ResultResponsePagination<KsdStoIssueAplctDto> {
        return readKsdIssueAplctUseCase.execute(query)
    }

    /**
     * 예탁원 공모신청승인 목록 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getKsdStoIssueAplct")
    @Operation(
        summary = "예탁원 공모신청승인 신청 목록 조회 API",
        description = "예탁원 공모신청승인 신청 목록 정보를 가져온다."
    )
    fun apprKsdStoIssueApplications(
        @RequestBody @Valid query: KsdIssueStoAplctQuery
    ): ResultResponse<KsdStoIssueDto> {
        return readKsdIssueUseCase.execute(query)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getKsdPostingInfo")
    @Operation(
        summary = "예탁원 게제정보 승인 조회 API",
        description = "예탁원 게제정보 승인조회 정보를 가져온다."
    )
    fun ksdPostingInfo(@RequestBody @Valid query: KsdPostingInfoQuery): ResultResponse<KsdPostingInfoDto> {
        return readKsdIssuePostingInfoUseCase.execute(query)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getKsdStoSbscrApplications")
    @Operation(
        summary = "예탁원 투자자 배정 승인 목록 조회 API",
        description = "예탁원 투자자 배정 승인 목록 조회 정보를 가져온다."
    )
    fun ksdStoSbscrApplications(@RequestBody @Valid query: KsdIssueStoSbscrAplctQuery): ResultResponse<KsdStoSbscrAplctDto> {
        return readKsdIssueStoSbscrAplctUseCase.execute(query)
    }

}
