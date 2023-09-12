package io.medium.poc.api.controller.isr.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "발행사 공모등록정보 조회 요청 객체")
data class IsrStoIssueRegistrationQuery(
    @Schema(description = "발행인번호")
    @field:NotNull(message = "발행인번호는 필수입니다.")
    @field:NotBlank(message = "발행인번호는 필수입니다.")
    val issueNo: String,

    @Schema(description = "발행관리기관번호")
    @field:NotNull(message = "발행관리기관번호는 필수입니다.")
    @field:NotBlank(message = "발행관리기관번호는 필수입니다.")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호")
    @field:NotNull(message = "발행관리기관계좌번호는 필수입니다.")
    @field:NotBlank(message = "발행관리기관계좌번호는 필수입니다.")
    val issueMgmtInstAcntNo: String,
)