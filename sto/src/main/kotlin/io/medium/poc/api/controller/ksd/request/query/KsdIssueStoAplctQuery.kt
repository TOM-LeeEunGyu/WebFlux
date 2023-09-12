package io.medium.poc.api.controller.ksd.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "예탁원 공모신청 승인 조회 요청 객체")
data class KsdIssueStoAplctQuery(
    @Schema(description = "발행관리기관번호")
    @field:NotBlank(message = "발행관리기관번호는 필수입니다.")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호")
    @field:NotBlank(message = "발행관리기관계좌번호는 필수입니다.")
    val issueMgmtInstAcntNo: String,

    @Schema(description = "발행인번호")
    @field:NotBlank(message = "발행인번호는 필수입니다.")
    val issueNo: String,
)