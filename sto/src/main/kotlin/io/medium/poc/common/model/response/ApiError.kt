package io.medium.poc.common.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "에러 응답 객체")
data class ApiError(
    @Schema(description = "에러 응답 코드")
    val code: Int,
    @Schema(description = "에러 응답 메세지")
    val message: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "에러 응답 일시", example = "1900-01-01 23:59:59")
    val timestamp: LocalDateTime,
)