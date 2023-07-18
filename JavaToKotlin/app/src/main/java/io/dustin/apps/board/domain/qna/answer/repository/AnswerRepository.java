package io.dustin.apps.board.domain.qna.answer.repository;

import io.dustin.apps.board.domain.qna.answer.model.Answer;
import io.dustin.apps.common.repository.BaseRepository;

public interface AnswerRepository extends BaseRepository<Answer, Long> {

    Answer findByQuestionId(Long questionId);
}
