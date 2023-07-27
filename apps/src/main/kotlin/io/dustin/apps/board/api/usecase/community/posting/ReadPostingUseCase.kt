package io.dustin.apps.board.api.usecase.community.posting

import ResponseWithScrollSetting.getCountByPagingInfo
import io.dustin.apps.board.api.usecase.community.comment.ReadCommentUseCase
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDetailDto
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.board.domain.community.posting.model.dto.PostingListDto
import io.dustin.apps.board.domain.community.posting.service.ReadPostingService
import io.dustin.apps.board.domain.community.posting.service.WritePostingService
import io.dustin.apps.common.model.CountByPagingInfo
import io.dustin.apps.common.model.QueryPage
import io.dustin.apps.common.model.ResponseWithScroll
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadPostingUseCase (

    private val readPostingService: ReadPostingService,
    private val writePostingService: WritePostingService,
    private val readCommentUseCase: ReadCommentUseCase
) {
    @Transactional
    fun execute(queryPage: QueryPage): ResponseWithScroll<List<PostingListDto>> {

        val userId: Long = 1
        val realSize: Long = queryPage.size
        val querySize = realSize + 1

        val result: List<PostingListDto> = readPostingService.getPostingList(userId, queryPage.nextId, querySize.toLong())
        val cbi: CountByPagingInfo<PostingListDto> = getCountByPagingInfo(result, realSize)
        return ResponseWithScroll.from(cbi.result, cbi.isLast, cbi.nextId)
    }
    @Transactional
    fun postingDetail(postingId: Long, queryPage: QueryPage): PostingDetailDto {

        val userId: Long = 1
        writePostingService.click(postingId)
        val posting: PostingDto = readPostingService.getPosting(userId, postingId)
        val commentList: ResponseWithScroll<List<CommentDto>> = readCommentUseCase.execute(postingId, queryPage)
        return PostingDetailDto.from(posting, commentList)
    }
}
