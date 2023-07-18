package io.dustin.apps.board.domain.qna.question.model.dto;

import io.dustin.apps.board.domain.qna.question.model.Question;
import io.dustin.apps.common.model.IdAble;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record QuestionDto(
        Long id,

        Long userId,

        String subject,

        String content,

        Boolean isComment,

        LocalDateTime createdAt
) implements IdAble {
        public static QuestionDto from(Question question) {
            return QuestionDto.builder()
                    .id(question.getId())
                    .userId(question.getUserId())
                    .subject(question.getSubject())
                    .content(question.getContent())
                    .createdAt(question.getCreatedAt())
                    .build();
        }

    @Override
    public Long getId() {
        return this.id;
    }

}
