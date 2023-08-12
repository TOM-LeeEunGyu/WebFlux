package io.dustin.apps.common.external

import io.dustin.apps.common.model.response.CommonResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

/**
 * feign이 적용된 엔드포인트 호출 interface
 */
@FeignClient(name = "dustin", url = "\${feign.client.config.test.url}")
interface TestCall {

    @PostMapping("/test")
    fun feignTest(str : String): CommonResponse

}
