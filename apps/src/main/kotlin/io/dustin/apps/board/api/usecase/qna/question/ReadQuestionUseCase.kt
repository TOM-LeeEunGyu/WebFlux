package io.dustin.apps.board.api.usecase.qna.question

import ResponseWithScrollSetting.getCountByPagingInfo
import io.dustin.apps.board.api.qna.request.query.QuestionDetailQuery
import io.dustin.apps.board.api.qna.request.query.QuestionListQuery
import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto
import io.dustin.apps.board.domain.qna.answer.service.ReadAnswerService
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDetailDto
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto
import io.dustin.apps.board.domain.qna.question.service.ReadQuestionService
import io.dustin.apps.board.domain.qna.question.service.WriteQuestionService
import io.dustin.apps.common.model.CountByPagingInfo
import io.dustin.apps.common.model.ResponseWithScroll
import io.dustin.apps.common.model.response.ResultResponse
import io.dustin.apps.common.model.response.ResultResponsePagination
import io.dustin.apps.common.utils.lastAt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadQuestionUseCase(
    private val readQuestionService: ReadQuestionService,
    private val writeQuestionService: WriteQuestionService,
    private val readAnswerService: ReadAnswerService
) {
    @Transactional
    fun execute(query: QuestionListQuery): ResultResponsePagination<QuestionDto> {

        val list = readQuestionService.getQuestionList(query.userId, query.recordsCount, query.nextId)
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
    fun questionDetail(query: QuestionDetailQuery):ResultResponse<QuestionDetailDto>{

        writeQuestionService.click(query.questionId)
        val question: QuestionDto = readQuestionService.getQuestion(query.userId, query.questionId)
        val answer: Answer = readAnswerService.findByQuestionId(query.questionId)
        val result = QuestionDetailDto.from(question, AnswerDto.from(answer))
        return ResultResponse.of(result)
    }
}
