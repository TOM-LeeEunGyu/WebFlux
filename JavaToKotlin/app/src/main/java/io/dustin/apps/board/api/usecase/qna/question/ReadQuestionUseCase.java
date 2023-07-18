package io.dustin.apps.board.api.usecase.qna.question;

import io.dustin.apps.board.domain.qna.answer.model.Answer;
import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto;
import io.dustin.apps.board.domain.qna.answer.service.ReadAnswerService;
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDetailDto;
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto;
import io.dustin.apps.board.domain.qna.question.service.ReadQuestionService;
import io.dustin.apps.board.domain.qna.question.service.WriteQuestionService;
import io.dustin.apps.common.model.CountByPagingInfo;
import io.dustin.apps.common.model.QueryPage;
import io.dustin.apps.common.model.ResponseWithScroll;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.dustin.apps.common.model.ResponseWithScrollSetting.getCountByPagingInfo;

@Service
@RequiredArgsConstructor
public class ReadQuestionUseCase {

    private final ReadQuestionService readQuestionService;
    private final WriteQuestionService writeQuestionService;
    private final ReadAnswerService readAnswerService;


    public ResponseWithScroll<List<QuestionDto>> execute(QueryPage queryPage) {
        /**
         * 게시물 리스트 보여줌
         */
        long userId = 1;
        int realSize = queryPage.getSize();
        int querySize = realSize + 1;

        List<QuestionDto> result = readQuestionService.getQuestionList(userId, queryPage.getNextId(), querySize);
        CountByPagingInfo<QuestionDto> cbi = getCountByPagingInfo(result, realSize);

        return ResponseWithScroll.from(cbi.result(), cbi.isLast(), cbi.nextId());

    }

    public QuestionDetailDto questionDetail (Long questionId) {

        long userId = 1;

        writeQuestionService.click(questionId);
        QuestionDto question = readQuestionService.getQuestion(userId, questionId);
        Answer answer = readAnswerService.findByQuestionId(questionId);

        return QuestionDetailDto.from(question, AnswerDto.from(answer));



    }


}
