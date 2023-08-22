package io.dustin.apps.board.api.usecase.qna.answer

import io.dustin.apps.board.api.qna.request.command.AnswerCommand
import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto
import io.dustin.apps.board.domain.qna.answer.service.WriteAnswerService
import io.dustin.apps.common.model.response.ResultResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteAnswerUseCase (
    private val writeAnswerService: WriteAnswerService,
) {

    @Transactional
    fun execute(command: AnswerCommand): ResultResponse<AnswerDto> {
        val answer: Answer = writeAnswerService.create(command.adminId, command.questionId, command.content)
        val result = AnswerDto.from(answer)
        return ResultResponse.of(result)
    }
}
