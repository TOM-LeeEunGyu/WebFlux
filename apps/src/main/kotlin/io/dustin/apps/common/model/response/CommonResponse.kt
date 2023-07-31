package io.dustin.apps.common.model.response
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "등록 및 업데이트 결과 공통 응답 객체")
data class CommonResponse(
    @Schema(description = "응답 코드", example = "성공: 200")
    val code: Int,
    @Schema(description = "응답 메세지", example = "success | fail")
    val message: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "응답 일시", example = "2023-07-28 13:44:18")
    val timestamp: LocalDateTime,
)
