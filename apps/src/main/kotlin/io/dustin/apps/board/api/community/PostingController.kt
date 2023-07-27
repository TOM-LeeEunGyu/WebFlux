package io.dustin.apps.board.api.community

import io.dustin.apps.board.api.community.request.PostingCreateCommand
import io.dustin.apps.board.api.community.request.PostingUpdateCommand
import io.dustin.apps.board.api.usecase.community.posting.DeletePostingUseCase
import io.dustin.apps.board.api.usecase.community.posting.ModifyPostingUseCase
import io.dustin.apps.board.api.usecase.community.posting.ReadPostingUseCase
import io.dustin.apps.board.api.usecase.community.posting.WritePostingUseCase
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDetailDto
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.common.model.QueryPage
import io.dustin.apps.common.model.ResponseWithScroll
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/posting")
class PostingController (

    private val readPostingUseCase: ReadPostingUseCase,
    private val writePostingUseCase: WritePostingUseCase,
    private val modifyPostingUseCase: ModifyPostingUseCase,
    private val deletePostingUseCase: DeletePostingUseCase

) {

    @GetMapping("/all")
    fun allPostings(queryPage: QueryPage): ResponseWithScroll<*> {
        return readPostingUseCase.execute(queryPage)
    }

    @GetMapping("/{postingId}")
    fun postingDetail(@PathVariable("postingId") postingId: Long, queryPage: QueryPage): PostingDetailDto {
        return readPostingUseCase.postingDetail(postingId, queryPage)
    }

    @PostMapping("/create")
    fun createPosting(@RequestBody command: PostingCreateCommand): PostingDto {
        return writePostingUseCase.execute(command.userId, command.subject, command.content)
    }

    @PatchMapping("/{postingId}")
    fun modifyPosting(
        @PathVariable("postingId") postingId: Long,
        @RequestBody command: PostingUpdateCommand,
    ): PostingDto {
        command.checkAssignment()
        return modifyPostingUseCase.execute(command.userId, postingId, command.subject, command.content)
    }

    @DeleteMapping("/{postingId}/user/{userId}")
    fun deletePosting(
        @PathVariable("postingId") postingId: Long,
        @PathVariable("userId") userId: Long,
    ): PostingDto {
        /** req 데이터 검증로직 추가 필요  */
        return deletePostingUseCase.execute(userId, postingId)
    }
}
