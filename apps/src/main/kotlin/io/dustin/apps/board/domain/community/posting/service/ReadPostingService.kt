package io.dustin.apps.board.domain.community.posting.service

import io.dustin.apps.board.domain.community.posting.repository.PostingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@RequiredArgsConstructor
class ReadPostingService {
    private val postingRepository: PostingRepository? = null
    @Transactional(readOnly = true)
    fun getPosting(userId: Long, postingId: Long): PostingDto {
        return postingRepository.getPosting(userId, postingId)
    }

    @Transactional(readOnly = true)
    fun getPostingList(userId: Long, nextId: Long?, size: Int): List<PostingListDto> {
        return postingRepository.getPostingList(userId, nextId, size)
    }

    @Transactional(readOnly = true)
    fun findById(postingId: Long): Posting? {
        val optional: Optional<Posting> = postingRepository.findById(postingId)
        return if (optional.isPresent()) {
            optional.get()
        } else {
            null
        }
    }

    @Transactional(readOnly = true)
    fun findByIdOrThrow(postingId: Long): Posting {
        val optional: Optional<Posting> = postingRepository.findById(postingId)
        return if (optional.isPresent()) {
            optional.get()
        } else {
            throw DataNotFoundException(
                """
                    id [#1]로 조회된 게시물이 없습니다.
                    """.trimIndent().replace("#1", postingId.toString())
            )
        }
    }
}
