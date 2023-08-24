package io.dustin.apps.board.api.qna

import io.dustin.apps.board.api.qna.request.command.AnswerCreateCommand
import io.dustin.apps.board.api.qna.request.command.AnswerDeleteCommand
import io.dustin.apps.board.api.qna.request.command.AnswerModifyCommand
import io.dustin.apps.board.api.usecase.qna.answer.DeleteAnswerUseCase
import io.dustin.apps.board.api.usecase.qna.answer.ModifyAnswerUseCase
import io.dustin.apps.board.api.usecase.qna.answer.WriteAnswerUseCase
import io.dustin.apps.board.domain.qna.answer.model.dto.AnswerDto
import io.dustin.apps.common.code.CommonMessage
import io.dustin.apps.common.model.response.CommonResponse
import io.dustin.apps.common.model.response.ResultResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/v1/answer")
@Tag(name = "1:1문의 답변 API", description = "1:1문의 답변 API 를 제공한다.")
class AnswerController (

    private val writeAnswerUseCase: WriteAnswerUseCase,
    private val modifyAnswerUseCase: ModifyAnswerUseCase,
    private val deleteAnswerUseCase: DeleteAnswerUseCase

) {
    /** =================== 1:1 문의에 대한 답변 endpoint =============== */

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crate")
    @Operation(
        summary = "1:1 문의의 답변을 작성한다",
        description = "1:1 문의의 답변을 작성한다"
    )
    fun createAnswer(
        @RequestBody @Valid command: AnswerCreateCommand
    ): ResultResponse<AnswerDto> {
        return writeAnswerUseCase.execute(command)
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/modify")
    @Operation(
        summary = "1:1 문의의 답변을 수정한다",
        description = "1:1 문의의 답변을 수정한다"
    )
    fun modifyAnswer(
        @RequestBody @Valid command: AnswerModifyCommand
    ): ResultResponse<AnswerDto> {

        return modifyAnswerUseCase.execute(command)
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete")
    @Operation(
        summary = "1:1 문의의 답변을 삭제한다",
        description = "1:1 문의의 답변을 삭제한다"
    )
    fun deleteAnswer(
        @RequestBody @Valid command: AnswerDeleteCommand
    ): CommonResponse {
        deleteAnswerUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }
}
