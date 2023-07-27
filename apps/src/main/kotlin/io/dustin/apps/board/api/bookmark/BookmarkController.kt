package io.dustin.apps.board.api.bookmark

import io.dustin.apps.board.api.usecase.bookmark.DeleteBookmarkUseCase
import io.dustin.apps.board.api.usecase.bookmark.WriteBookmarkUseCase
import io.dustin.apps.board.domain.bookmark.model.dto.BookMarkDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class BookmarkController (

    private val writeBookmarkUseCase: WriteBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase

) {

    @PostMapping("/bookmarks/add")
    fun add(@RequestBody bookMarkDto: BookMarkDto): BookMarkDto {
        return writeBookmarkUseCase.execute(bookMarkDto.userId, bookMarkDto.boardId)
    }

    @DeleteMapping("/bookmarks/cancel")
    fun cancel(@RequestBody bookMarkDto: BookMarkDto): BookMarkDto {
        return deleteBookmarkUseCase.execute(bookMarkDto.userId, bookMarkDto.boardId)
    }
}
