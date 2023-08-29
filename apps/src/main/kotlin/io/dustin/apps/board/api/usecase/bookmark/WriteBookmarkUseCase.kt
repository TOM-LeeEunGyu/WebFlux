package io.dustin.apps.board.api.usecase.bookmark

import io.dustin.apps.board.domain.bookmark.model.Bookmark
import io.dustin.apps.board.domain.bookmark.model.dto.BookMarkDto
import io.dustin.apps.board.domain.bookmark.service.ReadBookmarkService
import io.dustin.apps.board.domain.bookmark.service.WriteBookmarkService
import io.dustin.apps.common.utils.duplicate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteBookmarkUseCase (
    private val readBookmarkService: ReadBookmarkService,
    private val writeBookmarkService: WriteBookmarkService

) {
    @Transactional
    fun execute(userId: Long, boardId: Long): BookMarkDto {
        val bookmarkOjb = readBookmarkService.getBookmarkObj(userId,boardId)

        if(bookmarkOjb == null){
            val bookmark: Bookmark = writeBookmarkService.create(userId, boardId)
            return BookMarkDto.from(bookmark)
        }
        else{
            return throw duplicate("이미 북마크에 추가했습니다.")
        }

    }
}
