package io.dustin.apps.board.domain.qna.question.service;

import io.dustin.apps.board.domain.qna.question.model.Question;
import io.dustin.apps.board.domain.qna.question.repository.QuestionRepository;
import io.dustin.apps.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WriteQuestionService {

    private final QuestionRepository questionRepository;

    public Question create(Long userId, String subject, String content) {
        Question q = Question.builder()
                .userId(userId)
                .subject(subject)
                .content(content)
                .build();
        return questionRepository.save(q);
    }


    public void updateContent(Question question, String subject, String content) {
        question.updateSubject(subject);
        question.updateContent(content);
        questionRepository.save(question);
    }

    @Transactional
    public void delete(Question question){
        question.delete();
    }

    @Transactional
    public void click(long questionId){
        log.info("게시물 id : [" + questionId + "] 조회 수 증가");
        Question question = this.findByIdOrThrow(questionId);
        Long count = question.getClickCount() + 1;
        question.setClickCount(count);
    }


    public Question findById(long questionId) {
        Optional<Question> optional = this.questionRepository.findById(questionId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

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
