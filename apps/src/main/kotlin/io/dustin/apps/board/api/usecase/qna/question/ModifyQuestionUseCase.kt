package io.dustin.apps.board.api.usecase.qna.question

import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto
import io.dustin.apps.board.domain.qna.question.service.ReadQuestionService
import io.dustin.apps.board.domain.qna.question.service.WriteQuestionService
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ModifyQuestionUseCase (

    private val readQuestionService: ReadQuestionService,
    private val writeQuestionService: WriteQuestionService

) {
    @Transactional
    fun execute(userId: Long, questionId: Long, subject: String, content: String): QuestionDto {
        val question: Question = readQuestionService.findByIdOrThrow(questionId)
        writeQuestionService.updateContent(question, subject, content)
        return QuestionDto.from(question)
    }
}
