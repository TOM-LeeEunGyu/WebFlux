package io.dustin.apps.board.api.usecase.follow

import io.dustin.apps.board.domain.follow.model.dto.FollowDto
import io.dustin.apps.board.domain.follow.service.ReadFollowService
import io.dustin.apps.board.domain.follow.service.WriteFollowService
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteFollowUseCase (
    private val writeFollowService: WriteFollowService,
    private val readFollowService: ReadFollowService,
) {
    @Transactional
    fun execute(followerId: Long, followingId: Long): FollowDto {
        val follow = readFollowService.getFollow(followerId, followingId)
        writeFollowService.delete(followerId, followingId)
        return FollowDto.from(follow)
    }
}
