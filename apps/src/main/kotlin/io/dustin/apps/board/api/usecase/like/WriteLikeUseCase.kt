package io.dustin.apps.board.api.usecase.like

import io.dustin.apps.board.domain.like.model.dto.LikeDto
import io.dustin.apps.board.domain.like.service.ReadLIkeService
import io.dustin.apps.board.domain.like.service.WriteLikeService
import io.dustin.apps.common.code.BoardType
import io.dustin.apps.common.utils.duplicate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteLikeUseCase(
    private val readLIkeService: ReadLIkeService,
    private val writeLikeService: WriteLikeService
) {
    @Transactional
    fun execute(userId: Long, boardId: Long, boardType: BoardType): LikeDto {
        val likeOjb = readLIkeService.getLike(userId, boardId, boardType)

        if(likeOjb == null){
            val like = writeLikeService.create(userId, boardId, boardType)
            return LikeDto.from(like)
        }
        else{
            return throw duplicate("이미 좋아합니다 ~")
        }

    }
}
