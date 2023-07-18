package io.dustin.apps.board.api.usecase.qna.question;

import io.dustin.apps.board.domain.qna.question.model.Question;
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto;
import io.dustin.apps.board.domain.qna.question.service.ReadQuestionService;
import io.dustin.apps.board.domain.qna.question.service.WriteQuestionService;
import io.dustin.apps.board.domain.user.service.ReadUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class WriteQuestionUseCase {

    private final WriteQuestionService writeQuestionService;
    private final ReadQuestionService readQuestionService;
    private final ReadUserService readUserService;

    @Transactional
    public QuestionDto execute(Long userId, String subject, String content) {
        Question question = writeQuestionService.create(userId, subject, content);
        QuestionDto dto = QuestionDto.from(question);
        return QuestionDto.from(question);

    }

}
