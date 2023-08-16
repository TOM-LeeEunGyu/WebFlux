package io.dustin.apps.common.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.dustin.apps.common.code.CommonMessage
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

/**
 * Rest API response 정보를 담은 객체
 */
@Schema(description = "페이징 공통 결과 처리 정보")
@JsonPropertyOrder("bookmark, last, recordsCount, data")
data class ResultResponsePagination<T>(
    @Schema(description = "응답 코드", example = "성공: 200")
    val code: Int,

    @Schema(description = "응답 메세지", example = "success | fail")
    val message: String,

    @Schema(description = "다음 페이지 호출시 필요한 nextId 정보")
    val nextId: Long?,

    @Schema(description = "페이징 마지막 여부")
    val last: Boolean,

    @Schema(description = "현재 검색 결과 수")
    val recordsCount: Long,

    @Schema(hidden = true)
    private val _data: List<T>,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "응답 일시", example = "1900-01-01 23:59:59")
    val timestamp: LocalDateTime = LocalDateTime.now(),

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
        fun <T> of(
            nextId: Long? = null,
            last: Boolean,
            recordsCount: Long,
            data: List<T>
        ) = ResultResponsePagination(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            nextId = nextId,
            last = last,
            recordsCount = recordsCount,
            data
        )
    }

}