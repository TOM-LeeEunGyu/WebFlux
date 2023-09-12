package io.medium.poc.api.controller.sec.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "증권사 토큰증권관리(소각실행) 조회 객체")
data class SecIncinerationQuery(
    @Schema(description = "STO 종목번호")
    @field:NotNull(message = "STO 종목번호 정보는 필수입니다.")
    @field:NotBlank(message = "STO 종목번호 정보는 필수입니다.")
    val stoItemNo: String,

    @Schema(description = "고객관리기관번호")
    @field:NotNull(message = "고객관리기관번호 정보는 필수입니다.")
    @field:NotBlank(message = "고객관리기관번호 정보는 필수입니다.")
    val custMgmtInstNo: String,
)