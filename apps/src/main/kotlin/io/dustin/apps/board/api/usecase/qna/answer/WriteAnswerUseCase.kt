package io.dustin.apps.board.api.usecase.qna.answer

import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto
import io.dustin.apps.board.domain.qna.answer.service.WriteAnswerService
import org.springframework.stereotype.Service

@Service
class WriteAnswerUseCase (
    private val writeAnswerService: WriteAnswerService,
) {

    fun execute(adminId: Long, questionId: Long, content: String): AnswerDto {
        val answer: Answer = writeAnswerService.create(adminId, questionId, content)
        return AnswerDto.from(answer)
    }
}
