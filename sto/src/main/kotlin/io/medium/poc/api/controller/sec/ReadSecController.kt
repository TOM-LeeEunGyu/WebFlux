package io.medium.poc.api.controller.sec

import io.medium.poc.api.controller.sec.request.query.*
import io.medium.poc.api.usecase.sec.*
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.domain.sec.model.dto.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 증권사 read 컨트롤러
 */
@RestController
@RequestMapping("/api/poc/sec")
@Tag(name = "증권사 조회용 API", description = "증권사 관련 조회 API 를 제공한다.")
class ReadSecController(
    private val readCustMgmtInstCashTokenStateUseCase: ReadSecCustMgmtInstCashTokenStateUseCase,
    private val readCashTokenTradingUseCase: ReadSecCashTokenTradingUseCase,
    private val readSecCustAccountUseCase: ReadSecCustAccountUseCase,
    private val readSecComStoItemUseCase: ReadSecComStoItemUseCase,
    private val readSecAccountValidateUseCase: ReadSecAccountValidateUseCase,
) {

    /** =================== 증권사 관련 조회 endpoint =============== */

    /**
     * 증권사 고객관리기관 캐시토큰 상태 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getCustMgmtInstCashTokenState")
    @Operation(
        summary = "증권사 고객관리기관 캐시토큰 상태 조회 API",
        description = "증권사 고객관리기관 캐시토큰 상태 조회 정보를 가져온다."
    )
    fun secCustMgmtInstCashTokenState(
        @RequestBody @Valid query: SecCustMgmtInstCashTokenStateQuery
    ): ResultResponse<SecCustMgmtInstCashTokenStateDto> {
        return ResultResponse.of(readCustMgmtInstCashTokenStateUseCase.execute(query.compositeId()))
    }

    /**
     * 증권사 캐시토큰 거래내역 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getCashTokenTradingList")
    @Operation(
        summary = "증권사 캐시토큰 거래내역 조회 API",
        description = "증권사 캐시토큰 거래내역 조회 정보를 가져온다."
    )
    fun cashTokenTradingList(
        @RequestBody @Valid query: SecCashTokenTradingQuery
    ): ResultResponsePagination<SecCashTokenTradingDto> {
        return ResultResponsePagination.of(
            last = false,
            recordsCount = 10,
            data = readCashTokenTradingUseCase.execute(query)
        )
    }

    /**
     * 증권사 유저계좌 자산 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getUserAssetInfo")
    @Operation(
        summary = "증권사 유저계좌 자산 조회 API",
        description = "증권사 유저계좌에 대한 자산을 조회해서 가져온다"
    )
    fun getUserAssetInfo(
        @RequestBody @Valid query: SecCustAccountQuery
    ): ResultResponsePagination<AssetInfoDto> {
            return readSecCustAccountUseCase.execute(query)

    }

    /**
     * 증권사 토큰증권관리(소각실행) 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/incinerationList")
    @Operation(
        summary = "증권사 토큰증권관리(소각실행) 조회 API",
        description = "증권사 토큰증권관리(소각실행)을 조회해서 가져온다"
    )
    fun getBalanceQty(
        @RequestBody @Valid query: SecIncinerationQuery
    ): ResultResponse<SecIncinerationDto> {
        return ResultResponse.of(readSecComStoItemUseCase.execute(query))

    }

    /**
     * 위탁계좌유효성확인 리스트 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getSecAccountValidateList")
    @Operation(
        summary = "위탁계좌유효성확인 리스트 조회 API",
        description = "위탁계좌유효성확인 리스트를 조회한다."
    )
    fun getSecAccountValidateList(
        @RequestBody @Valid query: SecAcntValidateSearchQuery
    ): ResultResponsePagination<SecAccountValidateDto> {
        return readSecAccountValidateUseCase.execute(query)
    }

}
