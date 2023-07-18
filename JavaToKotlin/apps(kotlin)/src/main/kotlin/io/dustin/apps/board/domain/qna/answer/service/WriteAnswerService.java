package io.dustin.apps.board.domain.qna.answer.service;

import io.dustin.apps.board.domain.qna.answer.model.Answer;
import io.dustin.apps.board.domain.qna.answer.repository.AnswerRepository;
import io.dustin.apps.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WriteAnswerService {
    private final AnswerRepository answerRepository;

    public Answer create(Long adminId, Long questionId, String content){

        Answer answer = Answer.builder()
                .content(content)
                .questionId(questionId)
                .adminId(adminId)
                .build();
        this.answerRepository.save(answer);
        return answer;
    }

    @Transactional
    public void updateContent(Answer answer, String content){
        answer.updateContent(content);
    }

    @Transactional
    public void delete(Answer answer){
        answer.delete();
    }

    public Answer findById(long answerId) {
        Optional<Answer> optional = this.answerRepository.findById(answerId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    public Answer findByIdOrThrow(long answerId) {
        Optional<Answer> optional = this.answerRepository.findById(answerId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw new DataNotFoundException("""
                    id [#1]로 조회된 게시물이 없습니다.""".replace("#1", String.valueOf(answerId)));
        }
    }

}
