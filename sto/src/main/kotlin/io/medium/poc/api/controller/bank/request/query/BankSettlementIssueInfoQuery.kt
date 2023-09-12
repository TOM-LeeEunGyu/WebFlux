package io.medium.poc.api.controller.bank.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "은행 발행대금 정산처리 조회 요청 객체")
data class BankSettlementIssueInfoQuery(
    @Schema(description = "STO 종목번호")
    @field:NotNull(message = "STO 종목번호 정보는 필수입니다.")
    @field:NotBlank(message = "STO 종목번호 정보는 필수입니다.")
    val stoItemNo: String,
)