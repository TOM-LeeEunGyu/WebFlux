package io.dustin.apps.common.model.response

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.swagger.v3.oas.annotations.media.Schema

/**
 * Rest API response 정보를 담은 객체
 * created by basquiat
 */
@JsonPropertyOrder("result")
@Schema(description = "공통 결과 처리 정보")
data class ResultResponse<T>(

    @Schema(hidden = true)
    private val _data: T?,

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
        fun <T> of(data: T) = ResultResponse(data)
    }

}