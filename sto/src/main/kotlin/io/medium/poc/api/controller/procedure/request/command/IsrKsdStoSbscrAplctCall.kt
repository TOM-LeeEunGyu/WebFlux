package io.medium.poc.api.controller.procedure.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Schema(description = "투자자청약승인요청 프로시져 요청 객체")
data class IsrKsdStoSbscrAplctCall(
    @Schema(description = "발행관리기관번호(hidden)")
    @field:NotNull(message = "발행관리기관번호(hidden) 정보는 필수입니다.")
    @field:NotBlank(message = "발행관리기관번호(hidden) 정보는 필수입니다.")
    @field:Size(max = 5, message = "발행관리기관번호(hidden) 길이가 5를 넘을 수 없습니다.")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호(hidden)")
    @field:NotNull(message = "발행관리기관계좌번호(hidden) 정보는 필수입니다.")
    @field:NotBlank(message = "발행관리기관계좌번호(hidden) 정보는 필수입니다.")
    @field:Size(max = 20, message = "발행관리기관계좌번호(hidden) 길이가 20를 넘을 수 없습니다.")
    val issueMgmtInstAcntNo: String,

    @Schema(description = "STO 종목번호(hidden)")
    @field:NotNull(message = "STO 종목번호(hidden) 정보는 필수입니다.")
    @field:NotBlank(message = "STO 종목번호(hidden) 정보는 필수입니다.")
    @field:Size(max = 12, message = "STO 종목번호(hidden) 길이가 12를 넘을 수 없습니다.")
    val stoItemNo: String,
)