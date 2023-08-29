package io.dustin.apps.board.domain.like.service

import io.dustin.apps.board.domain.like.model.Like
import io.dustin.apps.board.domain.like.repository.LikeRepository
import io.dustin.apps.common.code.BoardType
import io.dustin.apps.common.utils.notFoundEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadLIkeService (

    private val likeRepository: LikeRepository

) {

    /**
     * 좋아요 객체 조회
     */
    @Transactional(readOnly = true)
    fun getLikeObj(userId: Long, boardId: Long, boardType: BoardType): Like? {
        return likeRepository.findByUsers(userId, boardId, boardType)

    }

    /**
     * 좋아요 객체 조회
     */
    @Transactional(readOnly = true)
    fun getLike(userId: Long, boardId: Long, boardType: BoardType): Like {
        val errorMessage = "해당 값: [$userId]로 조회된 정보가 없습니다."
        return likeRepository.findByUsers(userId, boardId, boardType) ?: notFoundEntity(errorMessage)

    }


}
