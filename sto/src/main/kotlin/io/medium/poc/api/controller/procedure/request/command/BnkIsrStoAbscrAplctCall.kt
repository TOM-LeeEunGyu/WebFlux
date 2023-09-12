package io.medium.poc.api.controller.procedure.request.command

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "청약증거금납입확인 프로시져 요청 객체")
data class BnkIsrStoAbscrAplctCall(
    @Schema(description = "입금일자")
    val depositDt: LocalDate,
)