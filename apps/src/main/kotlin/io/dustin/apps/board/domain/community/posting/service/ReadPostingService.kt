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

    /**
     * db 캐싱에 사용되는 임시 메소드
     */
    fun TestFindAll() =
        postingRepository.findAll()

    /**
     * 게시물 상세보기
     */
    fun getPosting(userId: Long, postingId: Long) =
        postingRepository.getPosting(userId, postingId)

    /**
     * 게시물 리스트 가져오기
     */
    fun getPostingList(userId: Long, recordsCount: Long, nextId: Long?) =
        postingRepository.getPostingList(userId, recordsCount, nextId)


    fun findByIdOrNull(postingId: Long): Posting? {
        return postingRepository.findByIdOrNull(postingId)
    }

    fun findByIdOrThrow(postingId: Long): Posting {
        return postingRepository.findByIdOrThrow(postingId)
    }
}
