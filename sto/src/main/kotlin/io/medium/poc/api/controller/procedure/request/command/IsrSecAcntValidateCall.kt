package io.medium.poc.api.controller.procedure.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "위탁계좌 유효성확인요청 프로시져 요청 객체")
data class IsrSecAcntValidateCall(

    @Schema(description = "발행관리기관번호")
    @field:NotNull(message = "발행관리기관번호 정보는 필수입니다.")
    @field:NotBlank(message = "발행관리기관번호 정보는 필수입니다.")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호")
    @field:NotNull(message = "발행관리기관계좌번호 정보는 필수입니다.")
    @field:NotBlank(message = "발행관리기관계좌번호 정보는 필수입니다.")
    val issueMgmtInstAcntNo: String,

    @Schema(description = "STO 종목번호")
    @field:NotNull(message = "STO 종목번호 정보는 필수입니다.")
    @field:NotBlank(message = "STO 종목번호 정보는 필수입니다.")
    val stoItemNo: String,
)