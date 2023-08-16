package io.dustin.apps.board.api.usecase.community.posting

import io.dustin.apps.board.api.community.request.query.AllPostingQuery
import io.dustin.apps.board.api.community.request.query.PostingDetailQuery
import io.dustin.apps.board.api.usecase.community.comment.ReadCommentUseCase
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDetailDto
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.board.domain.community.posting.model.dto.PostingListDto
import io.dustin.apps.board.domain.community.posting.service.ReadPostingService
import io.dustin.apps.board.domain.community.posting.service.WritePostingService
import io.dustin.apps.common.model.QueryPage
import io.dustin.apps.common.model.ResponseWithScroll
import io.dustin.apps.common.model.response.ResultResponse
import io.dustin.apps.common.model.response.ResultResponsePagination
import io.dustin.apps.common.utils.lastAt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadPostingUseCase (
    private val readPostingService: ReadPostingService,
    private val writePostingService: WritePostingService,
    private val readCommentUseCase: ReadCommentUseCase
) {
    @Transactional
    fun allPostingList(query: AllPostingQuery): ResultResponsePagination<PostingListDto> {
        val userId: Long = 1
        val list = with(query){
            readPostingService.getPostingList(userId, query.recordsCount, query.nextId)
        }
        println("몇개나 들어있을까요" + list.size)
        if(list.isEmpty()) {
            return ResultResponsePagination.of(
                last = true,
                recordsCount = 0,
                data = list,
            )
        }
        val result = list.take((query.recordsCount).toInt())
        val isLast = list.size <= query.recordsCount.toInt()

        val nextId = if (!isLast) {
            result.lastAt().id
        } else {
            null
        }

        return ResultResponsePagination.of(
            nextId = nextId,
            last = isLast,
            recordsCount = result.size.toLong(),
            data = result,
        )


    }
    @Transactional
    fun postingDetail(query: PostingDetailQuery): ResultResponse<PostingDetailDto> {

        val userId: Long = 1
        writePostingService.click(query.postingId)
        val posting: PostingDto = readPostingService.getPosting(userId, query.postingId)
        val commentList= readCommentUseCase.execute(query)
        val result = PostingDetailDto.from(posting, commentList)
        return ResultResponse.of(result)
    }

}

