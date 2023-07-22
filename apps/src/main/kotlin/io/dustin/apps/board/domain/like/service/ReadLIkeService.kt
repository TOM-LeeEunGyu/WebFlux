package io.dustin.apps.board.domain.like.service

import io.dustin.apps.board.domain.like.repository.LikeRepository
import io.dustin.apps.common.code.BoardType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadLIkeService (

    private val likeRepository: LikeRepository

) {
    @Transactional(readOnly = true)
    fun getLike(userId: Long, boardId: Long, boardType: BoardType) =
        likeRepository.findByUserIdAndBoardIdAndBoardType(userId, boardId, boardType)

}
