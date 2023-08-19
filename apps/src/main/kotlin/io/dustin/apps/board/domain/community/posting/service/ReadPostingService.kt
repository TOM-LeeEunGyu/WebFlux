package io.dustin.apps.board.domain.community.posting.service

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.board.domain.community.posting.repository.PostingRepository
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.repository.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadPostingService (
    private val postingRepository: PostingRepository
) {

    fun TestFindAll() =
        postingRepository.findAll()

    fun getPosting(userId: Long, postingId: Long) =
        postingRepository.getPosting(userId, postingId)


    fun getPostingList(userId: Long, recordsCount: Long, nextId: Long?) =
        postingRepository.getPostingList(userId, recordsCount, nextId)


    fun findByIdOrNull(postingId: Long): Posting? {
        return postingRepository.findByIdOrNull(postingId)
    }

    fun findByIdOrThrow(postingId: Long): Posting {
        return postingRepository.findByIdOrThrow(postingId)
    }
}
