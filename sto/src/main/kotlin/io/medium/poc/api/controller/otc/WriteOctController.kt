package io.medium.poc.api.controller.otc

import io.medium.poc.api.controller.otc.request.command.CancelOtcStoOrderBookCommand
import io.medium.poc.api.controller.otc.request.command.OtcEnforcementResultCommand
import io.medium.poc.api.controller.otc.request.command.OtcStoOrderBookCommand
import io.medium.poc.api.usecase.otc.WriteCancelOtcStoOrderBookUseCase
import io.medium.poc.api.usecase.otc.WriteOtcStoOrderBookUseCase
import io.medium.poc.api.usecase.otc.WriteOtcStoTradingUseCase
import io.medium.poc.common.code.CommonMessage
import io.medium.poc.common.model.response.CommonResponse
import io.medium.poc.common.utils.nowLocalDateTime
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/poc/oct")
@Tag(name = "장외거래 등록 및 업데이트 API", description = "장외거래 등록 및 업데이트 API 를 제공한다.")
class WriteOctController(
    private val writeOtcStoOrderBookUseCase: WriteOtcStoOrderBookUseCase,
    private val writeOtcStoTradingUseCase: WriteOtcStoTradingUseCase,
    private val writeCancelOtcStoOrderBookUseCase: WriteCancelOtcStoOrderBookUseCase,
) {
    /** =================== 장외거래 정보 endpoint =============== */

    /**
     * 장외거래중개업자_토큰증권 호가조회_호가등록
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create")
    @Operation(
        summary = "장외거래중개업자_토큰증권 호가를 등록한다 API",
        description = "장외거래중개업자_토큰증권 호가를 등록한다."
    )
    fun createOtcStoOrderBook(@RequestBody @Valid command: OtcStoOrderBookCommand): CommonResponse {
        writeOtcStoOrderBookUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 장외거래중개업자_토큰증권 호가조회_호가등록 취소
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/cancel")
    @Operation(
        summary = "장외거래중개업자_토큰증권 호가등록 호가 취소 API",
        description = "장외거래중개업자_토큰증권 호가등록을 취소한다."
    )
    fun cancelOtcStoOrderBook(@RequestBody @Valid command: CancelOtcStoOrderBookCommand): CommonResponse {
        writeCancelOtcStoOrderBookUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 장외거래중개업자_토큰증권 체결처리_체결처리
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/EnforcementResult")
    @Operation(
            summary = "장외거래중개업자_토큰증권 체결처리_체결처리 API",
            description = "장외거래중개업자_토큰증권 체결 처리한다"
    )
    fun otcEnforcementResult(@RequestBody @Valid command: OtcEnforcementResultCommand): CommonResponse {
        writeOtcStoTradingUseCase.execute(command)
        return CommonResponse(
                code = HttpStatus.OK.value(),
                message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

}
