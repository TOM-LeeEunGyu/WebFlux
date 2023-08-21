package io.dustin.apps.board.api.bookmark

import io.dustin.apps.board.api.bookmark.request.command.BookmarkCreateCommand
import io.dustin.apps.board.api.bookmark.request.command.BookmarkDeleteCommand
import io.dustin.apps.board.api.usecase.bookmark.DeleteBookmarkUseCase
import io.dustin.apps.board.api.usecase.bookmark.WriteBookmarkUseCase
import io.dustin.apps.board.domain.bookmark.model.dto.BookMarkDto
import io.dustin.apps.common.code.CommonMessage
import io.dustin.apps.common.model.response.CommonResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/bookmarks")
@Tag(name = "북마크 관련 API", description = "북마크 관련 API를 제공한다.")
class BookmarkController (

    private val writeBookmarkUseCase: WriteBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase

) {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    @Operation(
        summary = "북마크 추가",
        description = "북마크 추가"
    )
    fun add(@RequestBody @Valid command: BookmarkCreateCommand): CommonResponse {
        writeBookmarkUseCase.execute(command.userId, command.boardId)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/cancel")
    @Operation(
        summary = "북마크 해제",
        description = "북마크 해제"
    )
    fun cancel(@RequestBody @Valid command: BookmarkDeleteCommand): CommonResponse {
        deleteBookmarkUseCase.execute(command.userId, command.boardId)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }
}





