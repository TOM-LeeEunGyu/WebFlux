package io.dustin.apps.board.api.usecase.qna.answer

import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto
import io.dustin.apps.board.domain.qna.answer.service.ReadAnswerService
import io.dustin.apps.board.domain.qna.answer.service.WriteAnswerService
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.model.response.ResultResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ModifyAnswerUseCase (

    private val readAnswerService: ReadAnswerService,
    private val writeAnswerService: WriteAnswerService
) {
    @Transactional
    fun execute(adminId: Long, answerId: Long, content: String): ResultResponse<AnswerDto> {
        val answer = readAnswerService.findByIdOrThrow(answerId)
        writeAnswerService.updateContent(answer, content)
        val result = AnswerDto.from(answer)
        return ResultResponse.of(result)
    }
}
