package io.dustin.apps.board.domain.bookmark.service

import io.dustin.apps.board.domain.bookmark.model.Bookmark
import io.dustin.apps.board.domain.bookmark.repository.BookmarkRepository
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.utils.notFoundEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadBookmarkService (
    private val bookmarkRepository: BookmarkRepository

) {
    /**
     * 북마크 객체(단일) 조회
     */
    fun getBookmarkObj(userId: Long, boardId: Long): Bookmark? {
        return bookmarkRepository.findByUserIdAndBoardId(userId, boardId)
    }


    /**
     * 북마크 객체(단일) 조회
     */
    fun getBookmark(userId: Long, boardId: Long): Bookmark {
        val errorMessage = "조회된 정보가 없습니다."
        return bookmarkRepository.findByUserIdAndBoardId(userId, boardId) ?: notFoundEntity(errorMessage)
    }


}
