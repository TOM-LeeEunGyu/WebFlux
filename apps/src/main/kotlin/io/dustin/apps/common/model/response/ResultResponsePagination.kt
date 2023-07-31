package io.dustin.apps.common.model.response

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.swagger.v3.oas.annotations.media.Schema

/**
 * Rest API response 정보를 담은 객체
 * created by basquiat
 */
@Schema(description = "페이징 공통 결과 처리 정보")
@JsonPropertyOrder("bookmark, last, recordsCount, data")
data class ResultResponsePagination<T>(

    @Schema(description = "다음 페이지 호출시 필요한 bookmark 정보")
    val bookmark: String,

    @Schema(description = "페이징 마지막 여부")
    val last: Boolean,

    @Schema(description = "현재 검색 결과 수")
    val recordsCount: Long,

    @Schema(hidden = true)
    private val _data: List<T>,

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
            bookmark: String,
            last: Boolean,
            recordsCount: Long,
            data: List<T>
        ) = ResultResponsePagination(bookmark, last, recordsCount, data)
    }

}