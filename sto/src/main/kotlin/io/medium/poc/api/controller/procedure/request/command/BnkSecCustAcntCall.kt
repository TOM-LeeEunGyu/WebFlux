package io.medium.poc.api.controller.procedure.request.command

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "발행대금정산처리 프로시져 요청 객체")
data class BnkSecCustAcntCall(
    @Schema(description = "STO 종목번호")
    val stoItemNo: String,
)