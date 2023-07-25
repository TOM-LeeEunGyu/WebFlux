package io.dustin.apps.board.api.usecase.follow

import io.dustin.apps.board.domain.follow.model.dto.FollowDto
import io.dustin.apps.board.domain.follow.service.WriteFollowService
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteFollowUseCase (
    private val writeFollowService: WriteFollowService
) {
    @Transactional
    fun execute(followerId: Long, followingId: Long): FollowDto {
        val follow = writeFollowService.getFollow(followerId, followingId) ?:  throw DataNotFoundException("존재하지 않는 데이터입니다.")
        writeFollowService.delete(followerId, followingId)
        return FollowDto.from(follow)
    }
}
