package io.dustin.apps.board.api.usecase.qna.question;

import io.dustin.apps.board.domain.qna.question.model.Question;
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto;
import io.dustin.apps.board.domain.qna.question.service.ReadQuestionService;
import io.dustin.apps.board.domain.qna.question.service.WriteQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ModifyQuestionUseCase {

    private final ReadQuestionService readQuestionService;
    private final WriteQuestionService writeQuestionService;

    public QuestionDto execute(Long userId, Long questionId,  String subject, String content) {
        Question question = readQuestionService.findById(questionId);
        if (!question.getUserId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        writeQuestionService.updateContent(question, subject, content);
        return QuestionDto.from(question);
    }
}
