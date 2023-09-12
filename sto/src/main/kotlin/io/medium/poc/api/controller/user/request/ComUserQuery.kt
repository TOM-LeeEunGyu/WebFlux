package io.medium.poc.api.controller.user.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "로그인 요청 객체")
data class ComUserQuery(
    @Schema(description = "사용자 아이디")
    @field:NotNull(message = "사용자 아이디 정보는 필수입니다.")
    @field:NotBlank(message = "사용자 아이디 정보는 필수입니다.")
    val userId: String,
    @Schema(description = "사용자 비밀번호")
    @field:NotNull(message = "사용자 비밀번호 정보는 필수입니다.")
    @field:NotBlank(message = "사용자 비밀번호 정보는 필수입니다.")
    val userPw: String,
)