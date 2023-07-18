package io.dustin.apps.board.domain.qna.question.repository.custom;

import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto;

import java.util.List;

public interface CustomQuestionRepository {

    QuestionDto getQuestion(long userId, long questionId);

    List<QuestionDto> getQuestionList(long userId, Long nextId, int size);
}
