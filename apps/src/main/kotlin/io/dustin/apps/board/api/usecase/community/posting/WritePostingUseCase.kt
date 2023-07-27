package io.dustin.apps.board.api.usecase.community.posting

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.board.domain.community.posting.service.WritePostingService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WritePostingUseCase (

    private val writePostingService: WritePostingService

) {
    @Transactional
    fun execute(userId: Long, subject: String, content: String): PostingDto {
        val posting: Posting = writePostingService.create(userId, subject, content)
        return PostingDto.from(posting)
    }
}
