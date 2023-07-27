package io.dustin.apps.board.api.qna

import io.dustin.apps.board.api.usecase.qna.question.DeleteQuestionUseCase
import io.dustin.apps.board.api.usecase.qna.question.ModifyQuestionUseCase
import io.dustin.apps.board.api.usecase.qna.question.ReadQuestionUseCase
import io.dustin.apps.board.api.usecase.qna.question.WriteQuestionUseCase
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDetailDto
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto
import io.dustin.apps.common.model.QueryPage
import io.dustin.apps.common.model.ResponseWithScroll
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/question")
class QuestionController (

    private val readQuestionUseCase: ReadQuestionUseCase,
    private val writeQuestionUseCase: WriteQuestionUseCase,
    private val modifyQuestionUseCase: ModifyQuestionUseCase,
    private val deleteQuestionUseCase: DeleteQuestionUseCase,

    ) {

    @GetMapping("/all")
    fun allPostings(queryPage: QueryPage): ResponseWithScroll<*> {
        return readQuestionUseCase.execute(queryPage)
    }

    @GetMapping("/{questionId}")
    fun postingDetail(@PathVariable("questionId") questionId: Long): QuestionDetailDto {
        return readQuestionUseCase.questionDetail(questionId)
    }

    @PostMapping("/create")
    fun createQuestion(@RequestBody questionDto: QuestionDto): QuestionDto {
        return writeQuestionUseCase.execute(questionDto.userId, questionDto.subject, questionDto.content)
    }

    @PatchMapping("/{questionId}")
    fun modifyQuestion(
        @PathVariable("questionId") questionId: Long,
        @RequestBody questionDto: QuestionDto
    ): QuestionDto {
        return modifyQuestionUseCase.execute(
            questionDto.userId,
            questionId,
            questionDto.subject,
            questionDto.content
        )
    }

    @DeleteMapping("/{questionId}")
    fun deleteQuestion(
        @PathVariable("questionId") questionId: Long,
        @RequestBody questionDto: QuestionDto
    ): QuestionDto {
        return deleteQuestionUseCase.execute(questionDto.userId, questionId)
    }
}
