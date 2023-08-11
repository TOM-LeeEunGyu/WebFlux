package io.dustin.apps.board.api.usecase.bookmark

import io.dustin.apps.board.domain.bookmark.model.dto.BookMarkDto
import io.dustin.apps.board.domain.bookmark.service.ReadBookmarkService
import io.dustin.apps.board.domain.bookmark.service.WriteBookmarkService
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteBookmarkUseCase (
    private val writeBookmarkService: WriteBookmarkService,
    private val readBookmarkService: ReadBookmarkService

) {
    @Transactional
    fun execute(userId: Long, boardId: Long): BookMarkDto {
        val bookmark = readBookmarkService.getBookmark(userId, boardId)
        writeBookmarkService.delete(userId, boardId)
        return BookMarkDto.from(bookmark)
    }
}
