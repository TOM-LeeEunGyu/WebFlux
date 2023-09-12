package io.medium.poc.domain.user.model.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 공통 사용자 dto
 */
@Schema(description = "공통 사용자 응답 객체")
data class ComUserDto(

    @Schema(description = "사용자 아이디")
    val userId: String? = null,

    @Schema(description = "사용자 이름")
    val userName: String? = null,

    @Schema(description = "사용자 권한")
    val userAuth: String? = null,

    @Schema(description = "유효성확인여부(Y or N)")
    val validationYN: String,

    @Schema(description = "결과처리 메시지")
    val resultMsg: String,
)