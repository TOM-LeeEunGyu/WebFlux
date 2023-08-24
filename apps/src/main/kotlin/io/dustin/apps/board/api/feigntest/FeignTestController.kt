package io.dustin.apps.board.api.feigntest

import io.dustin.apps.common.code.CommonMessage
import io.dustin.apps.common.model.response.CommonResponse
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/feign")
class FeignTestController (

) {

    @Hidden
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/test")
    fun feignTest(): CommonResponse {

        try {
            println("문제가 있을까요")
        } catch (e: Exception) {
            return CommonResponse(
                code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = CommonMessage.FAIL.code,
                timestamp = LocalDateTime.now()
            )
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )

    }


}
