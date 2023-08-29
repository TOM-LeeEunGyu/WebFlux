package io.dustin.apps.board.api.usecase.qna.answer

import io.dustin.apps.board.api.qna.request.command.AnswerCreateCommand
import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto
import io.dustin.apps.board.domain.qna.answer.service.ReadAnswerService
import io.dustin.apps.board.domain.qna.answer.service.WriteAnswerService
import io.dustin.apps.common.model.response.ResultResponse
import io.dustin.apps.common.utils.duplicate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteAnswerUseCase (
    private val readAnswerService: ReadAnswerService,
    private val writeAnswerService: WriteAnswerService,
) {

    @Transactional
    fun execute(command: AnswerCreateCommand): ResultResponse<AnswerDto> {
        val answerObj = readAnswerService.getAnswerObj(command.questionId)

        if(answerObj == null){
            val answer: Answer = writeAnswerService.create(command.adminId, command.questionId, command.content)
            val result = AnswerDto.from(answer)
            return ResultResponse.of(result)
        }
        else{
            return throw duplicate("문의에 대한 답변은 하나만 가능합니다.")
        }

    }
}
