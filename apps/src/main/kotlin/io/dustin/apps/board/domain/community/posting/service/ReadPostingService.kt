package io.dustin.apps.board.domain.community.posting.service

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.board.domain.community.posting.repository.PostingRepository
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ReadPostingService (
    private val postingRepository: PostingRepository
) {

    @Transactional(readOnly = true)
    fun getPosting(userId: Long, postingId: Long) =
        postingRepository.getPosting(userId, postingId)


    @Transactional(readOnly = true)
    fun getPostingList(userId: Long, nextId: Long?, size: Int) =
        postingRepository.getPostingList(userId, nextId, size)


    fun findById(postingId: Long): Posting {
        return postingRepository.findById(postingId).orElse(null)
    }

    fun findByIdOrThrow(postingId: Long): Posting {
        return postingRepository.findById(postingId)
            .orElseThrow {
                DataNotFoundException("id [#1]로 조회된 댓글이 없습니다.".trimIndent().replace("#1", postingId.toString()))
            }
    }
}
