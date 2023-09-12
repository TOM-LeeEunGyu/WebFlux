package io.medium.poc.api.controller.ksd.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "예탁원 게제정보 승인 조회 요청 객체")
data class KsdPostingInfoQuery(
    @Schema(description = "발행관리기관번호")
    @field:NotNull(message = "발행관리기관번호는 필수입니다.")
    @field:NotBlank(message = "발행관리기관번호는 필수입니다.")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호")
    @field:NotNull(message = "발행관리기관계좌번호는 필수입니다.")
    @field:NotBlank(message = "발행관리기관계좌번호는 필수입니다.")
    val issueMgmtInstAcntNo: String,

    @Schema(description = "STO 종목번호")
    @field:NotNull(message = "STO 종목번호는 필수입니다.")
    @field:NotBlank(message = "STO 종목번호는 필수입니다.")
    val stoItemNo: String,
)