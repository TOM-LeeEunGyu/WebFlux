package io.dustin.apps.board.api.usecase.like

import io.dustin.apps.board.domain.like.model.dto.LikeDto
import io.dustin.apps.board.domain.like.service.ReadLIkeService
import io.dustin.apps.board.domain.like.service.WriteLikeService
import io.dustin.apps.common.code.BoardType
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteLikeUseCase (
    private val writeLikeService: WriteLikeService,
    private val readLIkeService: ReadLIkeService

) {
    @Transactional
    fun execute(userId: Long, boardId: Long, boardType: BoardType): LikeDto {
        val like = readLIkeService.getLike(userId, boardId, boardType) ?: throw DataNotFoundException("데이터가 없습니다.")
        writeLikeService.delete(userId, boardId, boardType)
        return LikeDto.from(like)
    }
}
