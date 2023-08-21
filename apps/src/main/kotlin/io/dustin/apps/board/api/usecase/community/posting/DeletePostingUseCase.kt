package io.dustin.apps.board.api.usecase.community.posting

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.board.domain.community.posting.service.ReadPostingService
import io.dustin.apps.board.domain.community.posting.service.WritePostingService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class DeletePostingUseCase (

    private val readPostingService: ReadPostingService,
    private val writePostingService: WritePostingService,
) {

    @Transactional
    fun execute(userId: Long, postingId: Long): PostingDto {
        val posting: Posting = readPostingService.findByIdOrThrow(postingId)
        writePostingService.delete(posting)
        return PostingDto.from(posting)
    }
}
