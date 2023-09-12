package io.medium.poc.common.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@Schema(description = "등록 및 업데이트 결과 공통 응답 객체")
data class CommonResponse(
    @Schema(description = "응답 코드", example = "성공: 200")
    val code: Int,
    @Schema(description = "응답 메세지", example = "success | fail")
    val message: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "응답 일시", example = "1900-01-01 23:59:59")
    val timestamp: LocalDateTime,
) {
    @Hidden
    fun isSuccess(): Boolean {
        return HttpStatus.OK.value() == code
    }
}
