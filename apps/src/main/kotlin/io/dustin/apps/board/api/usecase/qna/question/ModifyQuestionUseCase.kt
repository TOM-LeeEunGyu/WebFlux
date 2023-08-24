package io.dustin.apps.board.api.usecase.qna.question

import io.dustin.apps.board.api.qna.request.command.QuestionModifyCommand
import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto
import io.dustin.apps.board.domain.qna.question.service.ReadQuestionService
import io.dustin.apps.board.domain.qna.question.service.WriteQuestionService
import io.dustin.apps.common.model.response.ResultResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.naming.AuthenticationException

@Service
class ModifyQuestionUseCase (

    private val readQuestionService: ReadQuestionService,
    private val writeQuestionService: WriteQuestionService

) {
    @Transactional
    fun execute(command: QuestionModifyCommand): ResultResponse<QuestionDto> {

        val question: Question = readQuestionService.findByIdOrThrow(command.questionId)
        if(question.userId == command.userId){
            writeQuestionService.updateContent(question, command.subject, command.content)
            val result =  QuestionDto.from(question)
            return ResultResponse.of(result)
        }
        else{
            return throw AuthenticationException("권한이 없습니다.")
        }


    }
}
