package io.medium.poc.api.controller.invest

import io.medium.poc.api.controller.invest.request.command.InvestBankSbscrDepositCommand
import io.medium.poc.api.controller.invest.request.command.InvestStoSbscrAplctCommand
import io.medium.poc.api.usecase.invest.WriteInvestBankSbscrDepositUseCase
import io.medium.poc.api.usecase.invest.WriteInvestStoSbscrAplctUseCase
import io.medium.poc.common.code.*
import io.medium.poc.common.model.response.CommonResponse
import io.medium.poc.common.utils.nowLocalDateTime
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/poc/invest")
@Tag(name = "투자자 관련 신청 및 처리 API", description = "투자자 관련 신청 및 처리를 한다.")
class WriteInvestController(
    private val writeInvestBankSbscrDepositUseCase: WriteInvestBankSbscrDepositUseCase,
    private val writeInvestStoSbscrAplctUseCase: WriteInvestStoSbscrAplctUseCase,
) {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/registryInvestStoSbscrAplct")
    @Operation(
        summary = "투자자 청약신청 API",
        description = "투자자 청약신청을 한다."
    )
    fun registryInvestStoSbscrAplct(@RequestBody @Valid command: InvestStoSbscrAplctCommand): CommonResponse {
        writeInvestStoSbscrAplctUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/depositInvestorBankSbscr")
    @Operation(
        summary = "투자자 청약금 입금처리 API",
        description = "투자자 청약금 입금처리를 한다."
    )
    fun depositInvestorBankSbscr(@RequestBody @Valid command: InvestBankSbscrDepositCommand): CommonResponse {
        writeInvestBankSbscrDepositUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

}
