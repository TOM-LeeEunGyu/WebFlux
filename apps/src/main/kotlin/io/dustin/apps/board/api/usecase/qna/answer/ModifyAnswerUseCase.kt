package io.dustin.apps.board.api.usecase.qna.answer

import io.dustin.apps.board.api.qna.request.command.AnswerModifyCommand
import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto
import io.dustin.apps.board.domain.qna.answer.service.ReadAnswerService
import io.dustin.apps.board.domain.qna.answer.service.WriteAnswerService
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.model.response.ResultResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.naming.AuthenticationException

@Service
class ModifyAnswerUseCase (

    private val readAnswerService: ReadAnswerService,
    private val writeAnswerService: WriteAnswerService
) {
    @Transactional
    fun execute(command: AnswerModifyCommand): ResultResponse<AnswerDto> {
        val answer = readAnswerService.findByIdOrThrow(command.answerId)
        if(answer.adminId == command.adminId) {
            writeAnswerService.updateContent(answer, command.content)
            val result = AnswerDto.from(answer)
            return ResultResponse.of(result)
        }
        else{
            return throw AuthenticationException("권한이 없습니다.")
        }

    }
}
