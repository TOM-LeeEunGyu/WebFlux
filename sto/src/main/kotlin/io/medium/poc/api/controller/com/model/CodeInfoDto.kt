package io.medium.poc.api.controller.com.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "코드 목록 리스트 응답 객체")
data class CodeInfoDto(
    @Schema(description = "코드")
    val code: String,
    @Schema(description = "코드 설명")
    val description: String,
)