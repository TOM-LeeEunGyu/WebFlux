package io.dustin.apps.board.api.usecase.qna.question

import ResponseWithScrollSetting.getCountByPagingInfo
import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto
import io.dustin.apps.board.domain.qna.answer.service.ReadAnswerService
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDetailDto
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto
import io.dustin.apps.board.domain.qna.question.service.ReadQuestionService
import io.dustin.apps.board.domain.qna.question.service.WriteQuestionService
import io.dustin.apps.common.model.CountByPagingInfo
import io.dustin.apps.common.model.QueryPage
import io.dustin.apps.common.model.ResponseWithScroll
import org.springframework.stereotype.Service

@Service
class ReadQuestionUseCase(
    private val readQuestionService: ReadQuestionService,
    private val writeQuestionService: WriteQuestionService,
    private val readAnswerService: ReadAnswerService
) {

    fun execute(queryPage: QueryPage): ResponseWithScroll<List<QuestionDto>> {
        /**
         * 게시물 리스트 보여줌
         */
        val userId: Long = 1
        val realSize: Int = queryPage.size
        val querySize: Int = realSize + 1

        val result: List<QuestionDto> =
            readQuestionService.getQuestionList(userId, queryPage.nextId, querySize)
        val cbi: CountByPagingInfo<QuestionDto> = getCountByPagingInfo(result, realSize)

        return ResponseWithScroll.from(cbi.result, cbi.isLast(), cbi.nextId())
    }

    fun questionDetail(questionId: Long): QuestionDetailDto {
        val userId: Long = 1

        writeQuestionService.click(questionId)
        val question: QuestionDto = readQuestionService.getQuestion(userId, questionId)
        val answer: Answer? = readAnswerService.findByQuestionId(questionId)

        return QuestionDetailDto.from(question, AnswerDto.from(answer))
    }
}
