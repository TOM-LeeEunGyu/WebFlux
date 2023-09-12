package io.medium.poc.api.controller.bank

import io.medium.poc.api.controller.bank.request.query.*
import io.medium.poc.api.usecase.bank.*
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.domain.bank.model.dto.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 은행 캐시토큰 read 컨트롤러
 */
@RestController
@RequestMapping("/api/poc/bank")
@Tag(name = "은행 캐시토큰 조회용 API", description = "은행 캐시토큰 상태 조회 및 거래내역 조회 API 를 제공한다.")
class ReadBankCashTokenController(
    private val readCashTokenStateUseCase: ReadBankCashTokenStateUseCase,
    private val readCashTokenTradingUseCase: ReadBankCashTokenTradingUseCase,
    private val readCustMgmtInstCashTokenStateUseCase: ReadBankCustMgmtInstCashTokenStateUseCase,
    private val readBankStoIssueAplctUseCase: ReadBankStoIssueAplctUseCase,
    private val readBankSettlementIssueInfoUseCase: ReadBankSettlementIssueInfoUseCase,
) {

    /** =================== 은행 캐시 관련 처리 endpoint =============== */

    /**
     * 캐시토큰 상태 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getCashTokenState")
    @Operation(
        summary = "은행 캐시토큰 상태 조회 API",
        description = "은행 캐시토큰 상태 조회 정보를 가져온다."
    )
    fun cashTokenState(@RequestBody @Valid query: BankCashTokenStateQuery): ResultResponse<BankCashTokenStateDto> {
        return ResultResponse.of(readCashTokenStateUseCase.execute(query.compositeId()))
    }

    /**
     * 고객관리기관 캐시토큰 상태 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getCustMgmtInstCashTokenState")
    @Operation(
        summary = "은행 고객관리기관 캐시토큰 상태 조회 API",
        description = "은행 고객관리기관 캐시토큰 상태 조회 정보를 가져온다."
    )
    fun custMgmtInstCashTokenState(
        @RequestBody @Valid query: BankCustMgmtInstCashTokenStateQuery
    ): ResultResponse<BankCustMgmtInstCashTokenStateDto> {
        return ResultResponse.of(readCustMgmtInstCashTokenStateUseCase.execute(query.compositeId()))
    }

    /**
     * 캐시토큰 거래내역 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getCashTokenTradingList")
    @Operation(
        summary = "은행 캐시토큰 거래내역 조회 API",
        description = "은행 캐시토큰 거래내역 조회 정보를 가져온다."
    )
    fun cashTokenTradingList(
        @RequestBody @Valid query: BankCashTokenTradingQuery
    ): ResultResponsePagination<BankCashTokenTradingDto> {
        return ResultResponsePagination.of(
            last = false,
            recordsCount = 10,
            data = readCashTokenTradingUseCase.execute(query.compositeId(), query.start(), query.end())
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getBankStoIssueApplications")
    @Operation(
        summary = "은행 토큰증권 목록조회 API",
        description = "은행 토큰증권 목록 조회 리스트를 가져온다."
    )
    fun bankStoIssueApplications(
        @RequestBody @Valid query: BankSearchConditionQuery
    ): ResultResponsePagination<BankStoIssueAplctDto> {
        return readBankStoIssueAplctUseCase.execute(query)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getBankSettlementIssueInformation")
    @Operation(
        summary = "은행 발행대금 정산처리 조회 API",
        description = "은행 발행대금 정산처리 조회 정보를 가져온다."
    )
    fun bankSettlementIssueInformation(
        @RequestBody @Valid query: BankSettlementIssueInfoQuery
    ): ResultResponse<BankSettlementIssueInfoDto> {
        return readBankSettlementIssueInfoUseCase.execute(query)
    }

}