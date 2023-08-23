package io.dustin.apps.board.api.like

import io.dustin.apps.board.api.usecase.like.DeleteLikeUseCase
import io.dustin.apps.board.api.usecase.like.WriteLikeUseCase
import io.dustin.apps.board.domain.like.model.LikeCountService
import io.dustin.apps.board.domain.like.model.dto.LikeDto
import io.dustin.apps.board.domain.like.model.dto.LikeItCommand
import io.dustin.apps.common.code.CommonMessage
import io.dustin.apps.common.model.response.CommonResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1")
@Tag(name = "좋아요 관련 API", description = "좋아요를 누르거나 취소할 수 있는 API 를 제공한다.")
class LikeController (

    private val writeLikeUseCase: WriteLikeUseCase,
    private val deleteLikeUseCase: DeleteLikeUseCase,

) {

    @PostMapping("/like")
    fun like(@RequestBody @Valid command: LikeItCommand): CommonResponse {
        val lcs: LikeCountService = command.boardType.supplier()
        lcs.likeCount(command.boardId)
        writeLikeUseCase.execute(command.userId, command.boardId, command.boardType)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }

    @PatchMapping("/unlike")
    fun unlike(@RequestBody @Valid command: LikeItCommand): CommonResponse {
        val lcs: LikeCountService = command.boardType.supplier()
        lcs.likeCount(command.boardId)
        deleteLikeUseCase.execute(command.userId, command.boardId, command.boardType)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }
}
