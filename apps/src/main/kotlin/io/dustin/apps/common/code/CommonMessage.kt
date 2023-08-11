package io.dustin.apps.common.code

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 응답 공통 메세지
 */
@Schema(enumAsRef = true)
enum class CommonMessage(
    val code: String,
    val description: String,
) {
    SUCCESS("success", "성공"),
    FAIL("fail", "실패");
}
