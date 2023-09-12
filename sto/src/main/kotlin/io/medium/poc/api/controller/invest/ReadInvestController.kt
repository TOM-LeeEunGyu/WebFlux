package io.medium.poc.api.controller.invest

import io.medium.poc.api.controller.invest.request.query.InvestSearchConditionQuery
import io.medium.poc.api.controller.invest.request.query.InvestStoSbscrAplctQuery
import io.medium.poc.api.usecase.invest.ReadInvestStoIssueMgmtUseCase
import io.medium.poc.api.usecase.invest.ReadInvestStoSbscrAplctUseCase
import io.medium.poc.common.cache.CustomInvestorCacheManager
import io.medium.poc.common.code.*
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.domain.com.model.dto.ComInvestorDto
import io.medium.poc.domain.invest.model.dto.InvestStoIssueMgmtDto
import io.medium.poc.domain.invest.model.dto.InvestStoSbscrAplctDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/poc/invest")
@Tag(name = "투자자 관련 조회용 API", description = "투자자 관련 정보를 조회한다.")
class ReadInvestController(
    private val investorCache: CustomInvestorCacheManager,
    private val readInvestStoIssueMgmtUseCase: ReadInvestStoIssueMgmtUseCase,
    private val readInvestStoSbscrAplctUseCase: ReadInvestStoSbscrAplctUseCase,
) {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/investors")
    @Operation(
        summary = "투자자 투자자리스트 조회 API",
        description = "투자자 투자자리스트를 조회한다."
    )
    fun investors(): List<ComInvestorDto> = investorCache.comInvestors()

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getInvestStoIssueMgmt")
    @Operation(
        summary = "투자자 토큰증권 목록조회 API",
        description = "투자자 토큰증권 목록을 조회한다."
    )
    fun investStoIssueManagements(
        @RequestBody @Valid query: InvestSearchConditionQuery
    ): ResultResponsePagination<InvestStoIssueMgmtDto> {
        return readInvestStoIssueMgmtUseCase.execute(query)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getInvestStoSbscrAplct")
    @Operation(
        summary = "투자자 청약신청 조회 API",
        description = "투자자 청약신청 정보를 조회한다."
    )
    fun investStoSbscrApplication(
        @RequestBody @Valid query: InvestStoSbscrAplctQuery
    ): ResultResponse<InvestStoSbscrAplctDto> {
        return readInvestStoSbscrAplctUseCase.execute(query)
    }

}
