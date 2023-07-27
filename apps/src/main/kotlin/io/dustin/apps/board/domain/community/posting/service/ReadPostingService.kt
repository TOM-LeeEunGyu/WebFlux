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


    fun getPostingList(userId: Long, nextId: Long?, size: Long) =
        postingRepository.getPostingList(userId, nextId, size)


    fun useCase(id: Long) {
        val posting = this.findByIdOrThrow(id) // --> 우리가 의도한 대로

        //do something
    }

    fun useCaseWithNull(id: Long) {
        val posting = this.findByIdOrNull(id)

        posting?.let {
            TODO("포스팅 정보를 가지고 뭔갈를 한다.")
        } ?: TODO("기존의 어떤 값을 가지고 포스팅 객체 정보를 생성하거나 ????")
    }


    fun findByIdOrNull(postingId: Long): Posting? {
        return postingRepository.findByIdOrNull(postingId)
    }

    fun findByIdOrThrow(postingId: Long): Posting {
        return postingRepository.findByIdOrThrow(postingId)
    }
}
