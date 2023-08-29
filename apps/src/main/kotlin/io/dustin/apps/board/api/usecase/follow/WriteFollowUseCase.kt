package io.dustin.apps.board.api.usecase.follow

import io.dustin.apps.board.domain.follow.model.dto.FollowDto
import io.dustin.apps.board.domain.follow.service.ReadFollowService
import io.dustin.apps.board.domain.follow.service.WriteFollowService
import io.dustin.apps.common.utils.duplicate


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteFollowUseCase (
    private val readFollowService: ReadFollowService,
    private val writeFollowService: WriteFollowService
) {

    @Transactional
    fun execute(followerId: Long, followingId: Long): FollowDto {
        val followOjb = readFollowService.getFollowObj(followerId, followingId)

        if(followOjb == null){
            val follow  = writeFollowService.create(followerId, followingId)
            return FollowDto.from(follow)
        }
        else{
            return throw duplicate("이미 팔로우한 유저입니다.")
        }

    }
}
