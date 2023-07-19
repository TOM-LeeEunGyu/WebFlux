package io.dustin.apps.board.domain.qna.question.model.dto;

import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto;
import lombok.Builder;

@Builder
public record QuestionDetailDto (
        QuestionDto question,

        AnswerDto answer
) {
    public static QuestionDetailDto from(QuestionDto question, AnswerDto answer) {
        return QuestionDetailDto.builder()
                .question(question)
                .answer(answer)
                .build();

    }
}
