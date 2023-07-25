package io.dustin.apps.board.api.usecase.qna.question

import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.board.domain.qna.question.service.WriteQuestionService
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ReadQuestionUseCase {
    private val readQuestionService: ReadQuestionService? = null
    private val writeQuestionService: WriteQuestionService? = null
    private val readAnswerService: ReadAnswerService? = null
    fun execute(queryPage: QueryPage): ResponseWithScroll<List<QuestionDto>> {
        /**
         * 게시물 리스트 보여줌
         */
        val userId: Long = 1
        val realSize: Int = queryPage.getSize()
        val querySize = realSize + 1
        val result: List<QuestionDto> = readQuestionService.getQuestionList(userId, queryPage.getNextId(), querySize)
        val cbi: CountByPagingInfo<QuestionDto> = getCountByPagingInfo(result, realSize)
        return ResponseWithScroll.from(cbi.result(), cbi.isLast, cbi.nextId())
    }

    fun questionDetail(questionId: Long?): QuestionDetailDto {
        val userId: Long = 1
        writeQuestionService.click(questionId)
        val question: QuestionDto = readQuestionService.getQuestion(userId, questionId)
        val answer: Answer = readAnswerService.findByQuestionId(questionId)
        return QuestionDetailDto.from(question, AnswerDto.from(answer))
    }
}
