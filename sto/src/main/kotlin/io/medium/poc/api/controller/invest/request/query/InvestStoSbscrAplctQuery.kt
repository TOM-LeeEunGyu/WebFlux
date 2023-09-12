package io.medium.poc.api.controller.invest.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "투자자 청약신청 조회 요청 객체")
data class InvestStoSbscrAplctQuery(
    @Schema(description = "STO 종목번호")
    @field:NotBlank(message = "STO 종목번호는 필수입니다.")
    @field:NotNull(message = "STO 종목번호는 필수입니다.")
    val stoItemNo: String,

    @Schema(description = "고객관리기관번호")
    @field:NotBlank(message = "고객관리기관번호는 필수입니다.")
    @field:NotNull(message = "고객관리기관번호는 필수입니다.")
    val custMgmtInstNo: String,

    @Schema(description = "고객관리기관계좌번호")
    @field:NotBlank(message = "고객관리기관계좌번호는 필수입니다.")
    @field:NotNull(message = "고객관리기관계좌번호는 필수입니다.")
    val custMgmtInstAcntNo: String,
)