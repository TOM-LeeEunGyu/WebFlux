package io.dustin.apps.board.domain.like.repository

import io.dustin.apps.board.domain.like.model.Like
import io.dustin.apps.common.code.BoardType
import io.dustin.apps.common.repository.BaseRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface LikeRepository : BaseRepository<Like, Long> {

    @Query("SELECT s FROM Like s WHERE s.userId = :userId AND s.boardId = :boardId AND s.boardType = :boardType")
    fun findByUsers(userId: Long, boardId: Long, boardType: BoardType): Like?
    @Query("DELETE FROM Like s WHERE s.userId = :userId AND s.boardId = :boardId AND s.boardType = :boardType")
    fun deleteByUsers(userId: Long, boardId: Long, boardType: BoardType)

}
