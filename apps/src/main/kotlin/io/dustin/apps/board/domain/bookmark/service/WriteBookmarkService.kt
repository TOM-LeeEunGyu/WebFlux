package io.dustin.apps.board.domain.bookmark.service

import io.dustin.apps.board.domain.bookmark.model.Bookmark
import io.dustin.apps.board.domain.bookmark.repository.BookmarkRepository
import org.springframework.stereotype.Service

@Service
class WriteBookmarkService (
    private val bookmarkRepository: BookmarkRepository
) {

    /**
     * 북마크 추가
     */
    fun create(userId: Long, boardId: Long): Bookmark {
        val bookmark = Bookmark(
            userId = userId,
            boardId = boardId
        )
        bookmarkRepository.save(bookmark)
        return bookmark
    }

    /**
     * 북마크 해제
     */
    fun delete(userId: Long, boardId: Long) {
        bookmarkRepository.deleteByUserIdAndBoardId(userId, boardId)
    }
}
