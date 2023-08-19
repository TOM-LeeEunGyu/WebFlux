package io.dustin.apps.board.domain.qna.question.model.dto

import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto
import io.swagger.v3.oas.annotations.media.Schema


/**
 * 1:1문의 상세보기 dto
 */
@Schema(description = "1:1문의 상세보기 관련 응답 객체")
data class QuestionDetailDto(
    @Schema(description = "질문 객체")
    val question: QuestionDto,

    @Schema(description = "답변 객체")
    val answer: AnswerDto
) {
    companion object {
        fun from(question: QuestionDto, answer: AnswerDto): QuestionDetailDto {
            return QuestionDetailDto(question, answer)
        }
    }
}






