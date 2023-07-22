package io.dustin.apps.board.domain.like.repository

import io.dustin.apps.board.domain.like.model.Like
import io.dustin.apps.common.code.BoardType
import io.dustin.apps.common.repository.BaseRepository
import java.util.*

interface LikeRepository : BaseRepository<Like, Long?> {
    fun deleteByUserIdAndBoardIdAndBoardType(userId: Long, boardId: Long, boardType: BoardType)
    fun findByUserIdAndBoardIdAndBoardType(userId: Long, boardId: Long, boardType: BoardType): Like?
}
