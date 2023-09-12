package io.medium.poc.common.utils

import io.medium.poc.common.exception.FeignClientErrorException
import io.medium.poc.common.model.response.CommonResponse

/**
 * FeignClient 호출 성공 여부 체크
 * @param response
 * @param message
 */
fun validRpcResponse(response: CommonResponse, message: String) {
    if(!response.isSuccess()) {
        throw FeignClientErrorException(message)
    }
}