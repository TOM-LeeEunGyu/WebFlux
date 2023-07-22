package io.dustin.apps.board.domain.bookmark.service

import io.dustin.apps.board.domain.bookmark.model.Bookmark
import io.dustin.apps.board.domain.bookmark.repository.BookmarkRepository
import org.springframework.stereotype.Service

@Service
class WriteBookmarkService (
    private val bookmarkRepository: BookmarkRepository
) {

    fun create(userId: Long, boardId: Long): Bookmark {
        val bookmark = Bookmark(
            userId = userId,
            boardId = boardId
        )
        bookmarkRepository.save<Bookmark>(bookmark)
        return bookmark
    }

    fun delete(userId: Long, boardId: Long) {
        bookmarkRepository.deleteByUserIdAndBoardId(userId, boardId)
    }
}
