package io.medium.poc.api.controller.isr.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "발행사 투자자배정신청_투자자배정신청정보조회 조회 요청 객체")
data class IsrInvestorInfoQuery(

    @Schema(description = "발행관리기관번호")
    @field:NotNull(message = "발행인번호는 필수입니다.")
    @field:NotBlank(message = "발행인번호는 필수입니다.")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호")
    @field:NotNull(message = "발행관리기관계좌번호 필수입니다.")
    @field:NotBlank(message = "발행관리기관계좌번호 필수입니다.")
    val issueMgmtInstAcntNo: String,

    @Schema(description = "STO 종목번호")
    @field:NotNull(message = "STO 종목번호 필수입니다.")
    @field:NotBlank(message = "STO 종목번호 필수입니다.")
    val stoItemNo: String,

    @field:Min(1, message = "가져올 검색 결과 수는 0보다 커야 합니다.")
    @Schema(description = "가져올 검색 결과 수, 기본 값은 10", example = "10")
    val recordsCount: Long = 10,

    @Schema(description = "다음 페이지 호출시 필요한 bookmark 정보, 없으면 비운다.")
    val bookmark: Long = 0,
)