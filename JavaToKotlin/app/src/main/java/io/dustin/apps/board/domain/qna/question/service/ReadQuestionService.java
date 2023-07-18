package io.dustin.apps.board.domain.qna.question.service;

import io.dustin.apps.board.domain.qna.answer.model.Answer;
import io.dustin.apps.board.domain.qna.question.model.Question;
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto;
import io.dustin.apps.board.domain.qna.question.repository.QuestionRepository;
import io.dustin.apps.common.exception.DataNotFoundException;
import io.dustin.apps.board.domain.user.model.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadQuestionService {

    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public QuestionDto getQuestion(long userId, long questionId) {
        /**
         * clickCount 한개 증가시키기
         */
        return questionRepository.getQuestion(userId, questionId);
    }

    @Transactional(readOnly = true)
    public List<QuestionDto> getQuestionList(long userId, Long nextId, int size) {
        return questionRepository.getQuestionList(userId, nextId, size);
    }


    private Specification<Question> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L; // 직렬화 및 역직렬화시 그 값을 체크해주는 용도
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                /*
                q - Root, 즉 기준을 의미하는 Question 엔티티의 객체 (질문 제목과 내용을 검색하기 위해 필요)
                u1 - Question 엔티티와 SiteUser 엔티티를 아우터 조인(JoinType.LEFT)하여 만든 SiteUser 엔티티의 객체. Question 엔티티와 SiteUser 엔티티는 author 속성으로 연결되어 있기 때문에 q.join("author")와 같이 조인해야 한다. (질문 작성자를 검색하기 위해 필요)
                a - Question 엔티티와 Answer 엔티티를 아우터 조인하여 만든 Answer 엔티티의 객체. Question 엔티티와 Answer 엔티티는 answerList 속성으로 연결되어 있기 때문에 q.join("answerList")와 같이 조인해야 한다. (답변 내용을 검색하기 위해 필요)
                u2 - 바로 위에서 작성한 a 객체와 다시 한번 SiteUser 엔티티와 아우터 조인하여 만든 SiteUser 엔티티의 객체 (답변 작성자를 검색하기 위해서 필요)
                */
                query.distinct(true);
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자

            }
        };
    }

    @Transactional(readOnly = true)
    public Question findById(long questionId) {
        Optional<Question> optional = this.questionRepository.findById(questionId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Question findByIdOrThrow(long questionId) {
        Optional<Question> optional = this.questionRepository.findById(questionId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw new DataNotFoundException("""
                    id [#1]로 조회된 게시물이 없습니다.""".replace("#1", String.valueOf(questionId)));
        }
    }

}
