package io.medium.poc.api.controller.procedure.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "토큰증권 호가등록 프로시져 응답 객체")
data class OtcStoOrderBookCallResponse(

    @Schema(description = "응답 코드", example = "성공: 200")
    val code: Int,

    @Schema(description = "응답 메세지", example = "success | fail")
    val message: String,

    @Schema(description = "토큰증권 호가등록 프로시져 결과")
    val orderCkYn: Boolean,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "응답 일시", example = "1900-01-01 23:59:59")
    val timestamp: LocalDateTime,
)