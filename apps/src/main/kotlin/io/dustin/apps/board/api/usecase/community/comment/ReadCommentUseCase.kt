package io.dustin.apps.board.api.usecase.community.comment

import ResponseWithScrollSetting.getCountByPagingInfo
import io.dustin.apps.board.api.community.request.query.PostingDetailQuery
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.board.domain.community.comment.service.ReadCommentService
import io.dustin.apps.common.model.CountByPagingInfo
import io.dustin.apps.common.model.QueryPage
import io.dustin.apps.common.model.ResponseWithScroll
import io.dustin.apps.common.model.response.ResultResponsePagination
import io.dustin.apps.common.utils.lastAt
import org.springframework.stereotype.Service

@Service
class ReadCommentUseCase (

    private val readCommentService: ReadCommentService

) {
    fun execute(query: PostingDetailQuery): ResultResponsePagination<CommentDto> {

        val userId: Long = 1

        val list = readCommentService.getCommentsByPosting(userId, query.postingId, query.recordsCount, query.nextId)
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

    fun replyListByComment(commentId: Long, queryPage: QueryPage): ResponseWithScroll<List<CommentDto>> {

        val realSize: Long = queryPage.size
        val querySize = realSize + 1
        val userId: Long = 1

        val result: List<CommentDto> = readCommentService.replyListByComment(userId, commentId, querySize, queryPage.nextId)
        val cbi: CountByPagingInfo<CommentDto> = getCountByPagingInfo(result, realSize)
        return ResponseWithScroll.from(cbi.result, cbi.isLast, cbi.nextId)
    }
}