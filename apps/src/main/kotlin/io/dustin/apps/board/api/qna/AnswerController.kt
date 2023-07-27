package io.dustin.apps.board.api.qna

import io.dustin.apps.board.api.usecase.qna.answer.DeleteAnswerUseCase
import io.dustin.apps.board.api.usecase.qna.answer.ModifyAnswerUseCase
import io.dustin.apps.board.api.usecase.qna.answer.WriteAnswerUseCase
import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto
import io.dustin.apps.common.exception.BadRequestParameterException
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/answer")
class AnswerController (

    private val writeAnswerUseCase: WriteAnswerUseCase,
    private val modifyAnswerUseCase: ModifyAnswerUseCase,
    private val deleteAnswerUseCase: DeleteAnswerUseCase

) {

    @PostMapping("/questions/{questionId}")
    fun createAnswer(
        @PathVariable("questionId") questionId: Long,
        @RequestBody answerDto: AnswerDto
    ): AnswerDto {
        if (answerDto.content == null) {
            throw BadRequestParameterException("댓글 내용은 필수항목입니다.")
        }
        return writeAnswerUseCase.execute(answerDto.adminId, questionId, answerDto.content)
    }

    @PatchMapping("/{answerId}")
    fun modifyAnswer(
        @PathVariable("answerId") answerId: Long,
        @RequestBody answerDto: AnswerDto
    ): AnswerDto {
        if (answerDto.content == null) {
            throw BadRequestParameterException("댓글 내용은 필수항목입니다.")
        }
        return modifyAnswerUseCase.execute(answerDto.adminId, answerId, answerDto.content)
    }

    @DeleteMapping("/{answerId}")
    fun deleteAnswer(
        @PathVariable("answerId") answerId: Long,
        @RequestBody answerDto: AnswerDto
    ): AnswerDto {
        return deleteAnswerUseCase.execute(answerDto.adminId, answerId)
    }
}
