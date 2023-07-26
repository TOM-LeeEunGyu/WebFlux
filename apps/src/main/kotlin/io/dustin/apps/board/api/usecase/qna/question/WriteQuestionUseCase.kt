package io.dustin.apps.board.api.usecase.qna.question

import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto
import io.dustin.apps.board.domain.qna.question.service.ReadQuestionService
import io.dustin.apps.board.domain.qna.question.service.WriteQuestionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteQuestionUseCase (
    private val writeQuestionService: WriteQuestionService,
) {

    @Transactional
    fun execute(userId: Long, subject: String, content: String): QuestionDto {
        val question: Question = writeQuestionService.create(userId, subject, content)
        return QuestionDto.from(question)
    }
}
