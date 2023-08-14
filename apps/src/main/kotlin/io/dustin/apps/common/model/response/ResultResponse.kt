package io.dustin.apps.common.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.dustin.apps.common.code.CommonMessage
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.time.LocalDateTime.now

/**
 * Rest API response 정보를 담은 객체
 */
@JsonPropertyOrder("result")
@Schema(description = "공통 결과 처리 정보")
data class ResultResponse<T>(

    @Schema(description = "응답 코드")
    val code: Int,

    @Schema(description = "응답 메세지")
    val message: String,

    @Schema(hidden = true)
    private val _data: T?,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "응답 일시", example = "1900-01-01 23:59:59")
    val timestamp: LocalDateTime = now(),

    ) {

    val data
        @Schema(description = "요청 결과에 대한 응답 정보를 담는다.")
        get() = this._data

    companion object {
        /**
         * ResultResponse 를 생성하는 정적 메소드
         * @param data
         * @return ResultResponse<T>
         */
        fun <T> of(data: T) = ResultResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            data
        )
    }

}