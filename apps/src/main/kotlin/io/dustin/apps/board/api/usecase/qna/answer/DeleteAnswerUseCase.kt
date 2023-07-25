package io.dustin.apps.board.api.usecase.qna.answer

import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto
import io.dustin.apps.board.domain.qna.answer.service.ReadAnswerService
import io.dustin.apps.board.domain.qna.answer.service.WriteAnswerService
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteAnswerUseCase(
    private val readAnswerService: ReadAnswerService,
    private val writeAnswerService: WriteAnswerService
) {
    @Transactional
    fun execute(adminId: Long, answerId: Long): AnswerDto {
        val answer = readAnswerService.findById(answerId) ?: throw DataNotFoundException("조회된 데이터가 없습니다.")
        writeAnswerService.delete(answer)
        return AnswerDto.from(answer)
    }
}
