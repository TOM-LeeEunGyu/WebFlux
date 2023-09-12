package io.medium.poc.api.controller.bank

import io.medium.poc.api.controller.bank.request.command.BankCashTokenTradingCommand
import io.medium.poc.api.controller.bank.request.command.BankSettlementCommand
import io.medium.poc.api.usecase.bank.CallBankSettlementUseCase
import io.medium.poc.api.usecase.bank.WriteBankCashTokenTradingUseCase
import io.medium.poc.common.code.CommonMessage
import io.medium.poc.common.model.response.CommonResponse
import io.medium.poc.common.utils.nowLocalDateTime
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 은행 캐시토큰 write 컨트롤러
 */
@RestController
@RequestMapping("/api/poc/bank")
@Tag(name = "은행 캐시토큰 등록용 API", description = "은행 캐시토큰 거래요청 등록 API 를 제공한다.")
class WriteBankCashTokenController(
    private val writeCashTokenTradingUseCase: WriteBankCashTokenTradingUseCase,
    private val callBankSettlementUseCase: CallBankSettlementUseCase,
) {

    /** =================== 은행 캐시 관련 처리 endpoint =============== */

    /**
     * 캐시토큰 거래요청 등록
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/createCashTokenTrading")
    @Operation(
        summary = "은행 캐시토큰 거래요청 등록 API",
        description = "은행 캐시토큰 거래요청을 등록한다."
    )
    fun createCashTokenTrading(@RequestBody @Valid command: BankCashTokenTradingCommand): CommonResponse {
        writeCashTokenTradingUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 은행 발행대금정산처리
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/settlement")
    @Operation(
        summary = "은행 발행대금 정산처리 API",
        description = "은행 발행대금 정산처리한다."
    )
    fun settlement(@RequestBody @Valid command: BankSettlementCommand): CommonResponse {
        callBankSettlementUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

}