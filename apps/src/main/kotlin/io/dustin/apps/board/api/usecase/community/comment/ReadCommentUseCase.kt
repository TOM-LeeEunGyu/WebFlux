package io.dustin.apps.board.api.usecase.community.comment

import ResponseWithScrollSetting.getCountByPagingInfo
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.board.domain.community.comment.service.ReadCommentService
import io.dustin.apps.common.model.CountByPagingInfo
import io.dustin.apps.common.model.QueryPage
import io.dustin.apps.common.model.ResponseWithScroll
import org.springframework.stereotype.Service

@Service
class ReadCommentUseCase (

    private val readCommentService: ReadCommentService

) {
    fun execute(postingId: Long, queryPage: QueryPage): ResponseWithScroll<List<CommentDto>> {

        val realSize: Long = queryPage.size
        val querySize = realSize + 1
        val userId: Long = 1

        val result: List<CommentDto> = readCommentService.getCommentsByPosting(userId, postingId, querySize, queryPage.nextId)
        val cbi: CountByPagingInfo<CommentDto> = getCountByPagingInfo(result, realSize)
        return ResponseWithScroll.from(cbi.result, cbi.isLast, cbi.nextId)
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