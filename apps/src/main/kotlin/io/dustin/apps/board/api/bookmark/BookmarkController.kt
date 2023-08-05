package io.dustin.apps.board.api.bookmark

import io.dustin.apps.board.api.bookmark.request.command.BookmarkCreateCommand
import io.dustin.apps.board.api.bookmark.request.command.BookmarkDeleteCommand
import io.dustin.apps.board.api.usecase.bookmark.DeleteBookmarkUseCase
import io.dustin.apps.board.api.usecase.bookmark.WriteBookmarkUseCase
import io.dustin.apps.board.domain.bookmark.model.dto.BookMarkDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class BookmarkController (

    private val writeBookmarkUseCase: WriteBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase

) {

    @PostMapping("/bookmarks/add")
    fun add(@RequestBody @Valid command: BookmarkCreateCommand): BookMarkDto {
        return writeBookmarkUseCase.execute(command.userId, command.boardId)
    }

    @DeleteMapping("/bookmarks/cancel")
    fun cancel(@RequestBody @Valid command: BookmarkDeleteCommand): BookMarkDto {
        return deleteBookmarkUseCase.execute(command.userId, command.boardId)
    }
}
