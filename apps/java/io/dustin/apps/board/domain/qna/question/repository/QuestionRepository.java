package io.dustin.apps.board.domain.qna.question.repository;

import io.dustin.apps.board.domain.qna.question.model.Question;
import io.dustin.apps.board.domain.qna.question.repository.custom.CustomQuestionRepository;
import io.dustin.apps.common.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends BaseRepository<Question, Long>, CustomQuestionRepository {
    Optional<Question> findBySubject(String subject);
    Optional<Question> findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);

    Page<Question> findAll(Pageable pageable);
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);
}
