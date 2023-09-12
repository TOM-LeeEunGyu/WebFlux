package io.medium.poc.api.controller.sec

import io.medium.poc.api.controller.sec.request.command.SecAccountValidateCommand
import io.medium.poc.api.controller.sec.request.command.SecCashTokenTradingCommand
import io.medium.poc.api.controller.sec.request.command.SecIncinerationCommand
import io.medium.poc.api.usecase.sec.CallSecAccountValidateUseCase
import io.medium.poc.api.usecase.sec.WriteSecCashTokenTradingUseCase
import io.medium.poc.api.usecase.sec.WriteSecComStoItemUseCase
import io.medium.poc.common.code.CommonMessage
import io.medium.poc.common.model.response.CommonResponse
import io.medium.poc.common.utils.nowLocalDateTime
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 증권사 write 컨트롤러
 */
@RestController
@RequestMapping("/api/poc/sec")
@Tag(name = "증권사 등록 및 업데이트 API", description = "증권사 등록 및 업데이트 API 를 제공한다.")
class WriteSecController(
    private val writeCashTokenTradingUseCase: WriteSecCashTokenTradingUseCase,
    private val callSecAccountValidateUseCase: CallSecAccountValidateUseCase,
    private val writeSecComStoItemUseCase: WriteSecComStoItemUseCase,
) {

    /** =================== 증권사 관련 처리 endpoint =============== */

    /**
     * 증권사 캐시토큰 거래요청 등록
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/createCashTokenTrading")
    @Operation(
        summary = "증권사 캐시토큰 거래요청 등록 API",
        description = "증권사 캐시토큰 거래요청을 등록한다."
    )
    fun createCashTokenTrading(@RequestBody @Valid command: SecCashTokenTradingCommand): CommonResponse {
        writeCashTokenTradingUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 증권사 위탁계좌 유효성 검증 업데이트
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/verifyCustMgmtInstAcntNo")
    @Operation(
        summary = "증권사 위탁계좌 유효성 검증 업데이트 API",
        description = "증권사 위탁계좌 유효성을 업데이트한다."
    )
    fun verifyCustMgmtInstAcntNo(@RequestBody @Valid command: SecAccountValidateCommand): CommonResponse {
        callSecAccountValidateUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 증권사 토큰증권관리(소각실행)
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/updateIncineration")
    @Operation(
        summary = "증권사 토큰증권관리(소각실행) API",
        description = "증권사 토큰증권관리(소각실행)을 업데이트한다."
    )
    fun updateIncineration(@RequestBody @Valid command: SecIncinerationCommand): CommonResponse {
        writeSecComStoItemUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

}
