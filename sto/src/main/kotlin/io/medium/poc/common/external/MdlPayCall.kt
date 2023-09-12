package io.medium.poc.common.external

import io.medium.poc.common.external.request.SendTokenRequest
import io.medium.poc.common.external.request.TradeStoRequest
import io.medium.poc.common.external.response.SendTokenResponse
import io.medium.poc.common.external.response.TradeStoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

/**
 * MDL 호출 interface
 */
@FeignClient(name = "mdlpay", url = "\${feign.client.config.mdlpay.url}")
interface MdlPayCall {

    /**
     * 발행사가 예탁원으로 공모 신청 정보를 보낸다.
     */
    @PostMapping("/mdlpay")
    fun tradeSto(request: TradeStoRequest): TradeStoResponse

    /**
     * 토큰 전송
     */
    @PostMapping("/mdlpay")
    fun sendToken(request: SendTokenRequest): SendTokenResponse
}
