package io.medium.poc.api.controller.ksd

import io.medium.poc.api.controller.ksd.request.command.KsdStoIssueApproveCommand
import io.medium.poc.api.controller.ksd.request.command.KsdStoIssueMgmtApproveCommand
import io.medium.poc.api.controller.ksd.request.command.KsdStoSbscrAssignApproveCommand
import io.medium.poc.api.controller.procedure.request.command.KstStoSbscrAplctCall
import io.medium.poc.api.usecase.ksd.*
import io.medium.poc.common.code.CommonMessage
import io.medium.poc.common.model.response.CommonResponse
import io.medium.poc.common.utils.nowLocalDateTime
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 예탁원 write 컨트롤러
 */
@RestController
@RequestMapping("/api/poc/ksd")
@Tag(name = "예탁원 승인|등록|신청 API", description = "예탁원 승인|등록/신청 API 를 제공한다.")
class WriteKsdController(
    private val writeApproveKsdIssueAplctUseCase: WriteApproveKsdIssueAplctUseCase,
    private val writeApproveKsdIssueMgmtUseCase: WriteApproveKsdIssueMgmtUseCase,
    private val writeApproveKsdStoSbscrAssignUseCase: WriteApproveKsdStoSbscrAssignUseCase,
    private val callKsdStoSbscrAplctUseCase: CallKsdStoSbscrAplctUseCase,
) {

    /** =================== 예탁원 승인|등록/신청 endpoint =============== */

    /**
     * 예탁원 공모신청 승인
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/approveStoIssueApplication")
    @Operation(
        summary = "예탁원 공모신청승인 API",
        description = "예탁원 공모신청승인을 요청한다."
    )
    fun approveStoIssueApplication(@RequestBody @Valid command: KsdStoIssueApproveCommand): CommonResponse {
        writeApproveKsdIssueAplctUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 예탁원 게제정보 승인
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/approveStoIssueManagement")
    @Operation(
        summary = "예탁원 게제정보 승인 API",
        description = "예탁원 게제정보 승인을 요청한다."
    )
    fun approveStoIssueManagement(@RequestBody @Valid command: KsdStoIssueMgmtApproveCommand): CommonResponse {
        writeApproveKsdIssueMgmtUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 예탁원 투자자 배정 승인 처리
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/approveKsdStoSbscrAssign")
    @Operation(
        summary = "예탁원 투자자 배정 승인 처리 API",
        description = "예탁원 투자자 배정 승인 처리 요청한다."
    )
    fun approveKsdStoSbscrAssign(@RequestBody @Valid command: KsdStoSbscrAssignApproveCommand): CommonResponse {
        writeApproveKsdStoSbscrAssignUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 청약을 신청한 투자자의 투자한도를 확인하여 승인처리 프로시져 호출
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/callProcKSDKsdStoSbscrAplct")
    @Operation(
        summary = "청약을 신청한 투자자의 투자한도를 확인하여 승인처리 프로시져 호출 API",
        description = "청약을 신청한 투자자의 투자한도를 확인하여 승인처리 프로시져를 호출한다."
    )
    fun procKSDKsdStoSbscrAplct(@RequestBody @Valid command: KstStoSbscrAplctCall): CommonResponse {
        callKsdStoSbscrAplctUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

}
