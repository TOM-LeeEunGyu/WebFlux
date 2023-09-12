package io.medium.poc.api.controller.isr.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "발행사 게재정보접수_게재정보조회 조회 조건 요청 객체")
data class IsrStoIssuePubInfoQuery(
    @Schema(description = "발행인관리기관번호")
    @field:NotNull(message = "발행인관리기관번호는 필수입니다.")
    @field:NotBlank(message = "발행인관리기관번호는 필수입니다.")
    val issueMgmtInstNo:String,

    @Schema(description = "발행인관리기관계좌번호 번호")
    @field:NotNull(message = "발행인관리기관계좌번호는 필수입니다.")
    @field:NotBlank(message = "발행인관리기관계좌번호는 필수입니다.")
    val issueMgmtInstAcntNo:String,

    @Schema(description = "STO 종목번호")
    @field:NotNull(message = "STO 종목번호는 필수입니다.")
    @field:NotBlank(message = "STO 종목번호는 필수입니다.")
    val stoItemNo:String,

    @Schema(description = "발행인번호")
    @field:NotNull(message = "발행인번호는 필수입니다.")
    @field:NotBlank(message = "발행인번호는 필수입니다.")
    val issueNo:String,
)