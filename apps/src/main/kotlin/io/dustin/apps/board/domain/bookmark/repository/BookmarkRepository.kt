package io.dustin.apps.board.domain.bookmark.repository

import io.dustin.apps.board.domain.bookmark.model.Bookmark
import io.dustin.apps.common.repository.BaseRepository
import java.util.*

interface BookmarkRepository : BaseRepository<Bookmark, Long> {
    fun deleteByUserIdAndBoardId(userId: Long, boardId: Long)
    fun findByUserIdAndBoardId(userId: Long, boardId: Long): Bookmark?
}
