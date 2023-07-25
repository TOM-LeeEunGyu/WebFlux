package io.dustin.apps.board.api.usecase.bookmark

import io.dustin.apps.board.domain.bookmark.model.Bookmark
import io.dustin.apps.board.domain.bookmark.model.dto.BookMarkDto
import io.dustin.apps.board.domain.bookmark.service.WriteBookmarkService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteBookmarkUseCase (
    private val writeBookmarkService: WriteBookmarkService
) {
    @Transactional
    fun execute(userId: Long, boardId: Long): BookMarkDto {
        val bookmark: Bookmark = writeBookmarkService.create(userId, boardId)
        return BookMarkDto.from(bookmark)
    }
}
