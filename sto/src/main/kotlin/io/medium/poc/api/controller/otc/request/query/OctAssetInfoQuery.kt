package io.medium.poc.api.controller.otc.request.query


import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class OctAssetInfoQuery(

    @Schema(description = "고객관리기관번호")
    @field:NotNull(message = "고객관리기관번호 정보는 필수입니다.")
    @field:NotBlank(message = "고객관리기관번호 정보는 필수입니다.")
    val custMgmtInstNo: String,

    @Schema(description = "고객관리기관계좌번호")
    @field:NotNull(message = "고객관리기관계좌번호 정보는 필수입니다.")
    @field:NotBlank(message = "고객관리기관계좌번호 정보는 필수입니다.")
    val custMgmtInstAcntNo: String,

    @field:Min(1, message = "가져올 검색 결과 수는 0보다 커야 합니다.")
    @Schema(description = "가져올 검색 결과 수, 기본 값은 10", example = "10")
    val recordsCount: Long = 10,

    @Schema(description = "다음 페이지 호출시 필요한 bookmark 정보, 없으면 비운다.")
    val bookmark: Long? = null,
)