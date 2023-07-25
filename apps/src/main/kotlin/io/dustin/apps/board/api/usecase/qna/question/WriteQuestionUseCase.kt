package io.dustin.apps.board.api.usecase.qna.question

import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.board.domain.qna.question.service.WriteQuestionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class WriteQuestionUseCase {
    private val writeQuestionService: WriteQuestionService? = null
    private val readQuestionService: ReadQuestionService? = null
    private val readUserService: ReadUserService? = null
    @Transactional
    fun execute(userId: Long?, subject: String?, content: String?): QuestionDto {
        val question: Question = writeQuestionService.create(userId, subject, content)
        val dto: QuestionDto = QuestionDto.from(question)
        return QuestionDto.from(question)
    }
}
