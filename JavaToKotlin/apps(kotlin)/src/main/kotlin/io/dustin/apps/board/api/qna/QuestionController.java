package io.dustin.apps.board.api.qna;

import io.dustin.apps.board.api.usecase.qna.question.DeleteQuestionUseCase;
import io.dustin.apps.board.api.usecase.qna.question.ModifyQuestionUseCase;
import io.dustin.apps.board.api.usecase.qna.question.ReadQuestionUseCase;
import io.dustin.apps.board.api.usecase.qna.question.WriteQuestionUseCase;
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDetailDto;
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto;
import io.dustin.apps.common.model.QueryPage;
import io.dustin.apps.common.model.ResponseWithScroll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final ReadQuestionUseCase readQuestionUseCase;
    private final WriteQuestionUseCase writeQuestionUseCase;
    private final ModifyQuestionUseCase modifyQuestionUseCase;
    private final DeleteQuestionUseCase deleteQuestionUseCase;

    @GetMapping("/all")
    public ResponseWithScroll allPostings(QueryPage queryPage) {
        return readQuestionUseCase.execute(queryPage);
    }

    @GetMapping("/{questionId}")
    public QuestionDetailDto postingDetail(@PathVariable("questionId") Long questionId) {
        return readQuestionUseCase.questionDetail(questionId);
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public QuestionDto createQuestion(@RequestBody QuestionDto questionDto) {
        return  writeQuestionUseCase.execute(questionDto.userId(), questionDto.subject(), questionDto.content());
    }

    //@PreAuthorize("isAuthenticated()")
    @PatchMapping("/{questionId}")
    public QuestionDto modifyQuestion(@PathVariable("questionId") Long questionId,
                                      @RequestBody QuestionDto questionDto
                                  ) {
        return modifyQuestionUseCase.execute(questionDto.userId(), questionId, questionDto.subject(), questionDto.content());
    }

    //@PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{questionId}")
    public QuestionDto deleteQuestion(@PathVariable("questionId") Long questionId,
                                      @RequestBody QuestionDto questionDto) {
        return deleteQuestionUseCase.execute(questionDto.userId(), questionId);
    }




}
