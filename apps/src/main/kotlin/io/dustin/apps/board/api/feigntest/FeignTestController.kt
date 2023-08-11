package io.dustin.apps.board.api.feigntest

import io.dustin.apps.board.api.community.request.command.PostingCreateCommand
import io.dustin.apps.board.api.community.request.command.PostingUpdateCommand
import io.dustin.apps.board.api.usecase.community.posting.DeletePostingUseCase
import io.dustin.apps.board.api.usecase.community.posting.ModifyPostingUseCase
import io.dustin.apps.board.api.usecase.community.posting.ReadPostingUseCase
import io.dustin.apps.board.api.usecase.community.posting.WritePostingUseCase
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDetailDto
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.common.code.CommonMessage
import io.dustin.apps.common.model.QueryPage
import io.dustin.apps.common.model.ResponseWithScroll
import io.dustin.apps.common.model.response.CommonResponse
import io.dustin.apps.common.model.response.ResultResponse
import io.swagger.v3.oas.annotations.Hidden
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/feign")
class FeignTestController (

) {

    @Hidden
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/test")
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
