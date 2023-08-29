package io.dustin.apps.board.api.usecase.community.comment

import ResponseWithScrollSetting.getCountByPagingInfo
import io.dustin.apps.board.api.community.request.query.AllCommentQuery
import io.dustin.apps.board.api.community.request.query.CommentDetailQuery
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

    fun replyListByComment(query: CommentDetailQuery): ResultResponsePagination<CommentDto> {

        val userId: Long = 1

        val list: List<CommentDto> = readCommentService.replyListByComment(userId, query.commentId, query.recordsCount, query.nextId)
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


}