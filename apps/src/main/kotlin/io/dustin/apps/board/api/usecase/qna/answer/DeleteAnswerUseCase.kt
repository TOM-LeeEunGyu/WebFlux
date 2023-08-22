package io.dustin.apps.board.api.usecase.qna.answer

import io.dustin.apps.board.api.qna.request.command.AnswerDeleteCommand
import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto
import io.dustin.apps.board.domain.qna.answer.service.ReadAnswerService
import io.dustin.apps.board.domain.qna.answer.service.WriteAnswerService
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.naming.AuthenticationException

@Service
class DeleteAnswerUseCase(
    private val readAnswerService: ReadAnswerService,
    private val writeAnswerService: WriteAnswerService
) {
    @Transactional
    fun execute(command: AnswerDeleteCommand) {
        val answer = readAnswerService.findByIdOrThrow(command.answerId)
        if(answer.adminId == command.adminId){
            writeAnswerService.delete(answer)
        }
        else {
            return throw AuthenticationException("권한이 없습니다.")
        }
    }
}
