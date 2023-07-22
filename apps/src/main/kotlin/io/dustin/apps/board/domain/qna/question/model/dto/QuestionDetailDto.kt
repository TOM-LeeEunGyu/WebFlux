package io.dustin.apps.board.domain.qna.question.model.dto

import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto


data class QuestionDetailDto(

    val question: QuestionDto,
    val answer: AnswerDto
) {
    companion object {
        fun from(question: QuestionDto, answer: AnswerDto): QuestionDetailDto {
            return QuestionDetailDto(question, answer)
        }
    }
}






