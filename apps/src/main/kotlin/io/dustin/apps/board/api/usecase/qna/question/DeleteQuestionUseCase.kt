package io.dustin.apps.board.api.usecase.qna.question

import io.dustin.apps.board.api.qna.request.command.QuestionDeleteCommand
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto
import io.dustin.apps.board.domain.qna.question.service.ReadQuestionService
import io.dustin.apps.board.domain.qna.question.service.WriteQuestionService
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.model.response.CommonResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.naming.AuthenticationException

@Service
class DeleteQuestionUseCase (
    private val readQuestionService: ReadQuestionService,
    private val writeQuestionService: WriteQuestionService
) {
    @Transactional
    fun execute(command: QuestionDeleteCommand) {
        val question = readQuestionService.findByIdOrThrow(command.questionId)
        if(question.userId == command.userId){
            writeQuestionService.delete(question)
        }
        else{
            return throw AuthenticationException("권한이 없습니다.")
        }

    }
}
