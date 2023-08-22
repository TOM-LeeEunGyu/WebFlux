package io.dustin.apps.board.domain.restriction.posting.service

import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction
import io.dustin.apps.board.domain.restriction.posting.repository.PostingRestrictionRepository
import io.dustin.apps.common.utils.notFoundEntity
import org.springframework.stereotype.Service

@Service
class ReadPostingRestrictionService (
    private val postingRestrictionRepository: PostingRestrictionRepository
) {

    /**
     * 제한된 유저 객체 가져오기
     */
    fun getPostingRestrictionUser(fromUserId: Long, toUserId: Long, postingId: Long): PostingRestriction {
        val errorMessage = "조회된 정보가 없습니다."
        return postingRestrictionRepository.findByUsers(fromUserId, toUserId, postingId) ?: notFoundEntity(errorMessage)
    }

}
