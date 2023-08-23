package io.dustin.apps.board.api.usecase.community.posting

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.board.domain.community.posting.service.ReadPostingService
import io.dustin.apps.board.domain.community.posting.service.WritePostingService
import io.dustin.apps.common.model.response.ResultResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class ModifyPostingUseCase (

    private val readPostingService: ReadPostingService,
    private val writePostingService: WritePostingService

) {

    @Transactional
    fun execute(userId: Long, postingId: Long, subject: String, content: String): ResultResponse<PostingDto> {
        val posting: Posting = readPostingService.findByIdOrThrow(postingId)
        writePostingService.updateContent(posting, subject, content)
        val result = PostingDto.from(posting)
        return ResultResponse.of(result)
    }
}
