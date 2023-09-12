package io.medium.poc.api.controller.isr.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Schema(description = "발행사 투자자배정신청_투자자배정신청 요청 객체")
data class IsrStoSbscrAplctCommand(
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