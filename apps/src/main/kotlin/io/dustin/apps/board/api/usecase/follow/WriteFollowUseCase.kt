package io.dustin.apps.board.api.usecase.follow

import io.dustin.apps.board.domain.follow.model.dto.FollowDto
import io.dustin.apps.board.domain.follow.service.WriteFollowService


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteFollowUseCase (
    private val writeFollowService: WriteFollowService
) {

    @Transactional
    fun execute(followerId: Long, followingId: Long): FollowDto {
        val follow  = writeFollowService.create(followerId, followingId)
        return FollowDto.from(follow)
    }
}
