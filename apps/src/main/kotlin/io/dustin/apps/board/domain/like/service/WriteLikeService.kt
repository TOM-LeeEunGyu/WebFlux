package io.dustin.apps.board.domain.like.service

import io.dustin.apps.board.domain.like.model.Like
import io.dustin.apps.board.domain.like.repository.LikeRepository
import io.dustin.apps.common.code.BoardType
import org.springframework.stereotype.Service

@Service
class WriteLikeService (
    val likeRepository: LikeRepository
) {

    /**
     * 좋아요
     */
    fun create(userId: Long, boardId: Long, boardType: BoardType): Like {
        val like = Like(
            userId = userId,
            boardId = boardId,
            boardType = boardType
        )
        likeRepository.save(like)
        return like
    }

    /**
     * 좋아요 취소
     */
    fun delete(userId: Long, boardId: Long, boardType: BoardType) {
        likeRepository.deleteByUsers(userId, boardId, boardType)
    }
}
