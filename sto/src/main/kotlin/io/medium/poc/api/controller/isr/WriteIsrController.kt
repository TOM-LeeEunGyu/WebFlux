package io.medium.poc.api.controller.isr

import io.medium.poc.api.controller.isr.request.command.IsrSecAcntValidateCommand
import io.medium.poc.api.controller.isr.request.command.IsrStoIssueMgmtCommand
import io.medium.poc.api.controller.isr.request.command.IsrStoIssueRegistrationCommand
import io.medium.poc.api.controller.isr.request.command.IsrStoSbscrAplctCommand
import io.medium.poc.api.controller.procedure.request.command.IsrStoRightCall
import io.medium.poc.api.usecase.isr.*
import io.medium.poc.common.code.CommonMessage
import io.medium.poc.common.model.response.CommonResponse
import io.medium.poc.common.utils.nowLocalDateTime
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 발행사 write 컨트롤러
 */
@RestController
@RequestMapping("/api/poc/isr")
@Tag(name = "발행사 등록 및 업데이트 API", description = "발행사 등록 및 업데이트 API 를 제공한다.")
class WriteIsrController(
    private val writeIsrRegistrationUseCase: WriteIsrRegistrationUseCase,
    private val writeIsrStoIssueMgmtUseCase: WriteIsrStoIssueMgmtUseCase,
    private val callIsrStoSbscrAplctUseCase: CallIsrStoSbscrAplctUseCase,
    private val callIsrSecAcntValidateUseCase: CallIsrSecAcntValidateUseCase,
    private val callIsrCreateStoRightUseCase: CallIsrCreateStoRightUseCase,
) {
    /** =================== 발행사 등록 관련 endpoint =============== */

    /**
     * 발행사 공모 등록
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    @Operation(
        summary = "발행사 공모신청 등록 API",
        description = "발행사 공모신청 내용을 등록하고 예탁원으로 공모신청 내용을 전송한다."
    )
    fun createIsrStoIssueAplct(@RequestBody @Valid command: IsrStoIssueRegistrationCommand): CommonResponse {
        writeIsrRegistrationUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 발행사 투자자배정신청_투자자배정신청 전송
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createIsrStoSbscrAplct")
    @Operation(
        summary = "발행사 투자자배정신청_투자자배정신청 전송 프로시져 호출 API",
        description = "발행사 투자자배정신청_투자자배정신청 -> 전송 프로시져 호출"
    )
    fun createIsrStoSbscrAplct(@RequestBody @Valid command: IsrStoSbscrAplctCommand): CommonResponse {
        callIsrStoSbscrAplctUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 발행사 게재정보접수_게재정보등록
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createIsrStoIssueMgmt")
    @Operation(
            summary = "발행사 게재정보접수_게재정보등록 API",
            description = "발행사 게재정보접수_게재정보등록"
    )
    fun createIsrStoIssueMgmt(@RequestBody @Valid command: IsrStoIssueMgmtCommand): CommonResponse {
        writeIsrStoIssueMgmtUseCase.execute(command)
        return CommonResponse(
                code = HttpStatus.OK.value(),
                message = CommonMessage.SUCCESS.code,
                timestamp = nowLocalDateTime()
        )
    }

    /**
     * 위탁계좌 유효성확인요청
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/callSecAcntValidate")
    @Operation(
        summary = "위탁계좌 유효성확인요청 청약신청시에 고객이 등록한 고객계좌에 대한 유효성 확인을 증권사에 요청하는 프로시져를 호출 API",
        description = "청약신청시에 고객이 등록한 고객계좌에 대한 유효성 확인을 증권사에 요청하는 프로시져를 호출한다."
    )
    fun secAcntValidate(@RequestBody @Valid command: IsrSecAcntValidateCommand): CommonResponse {
        callIsrSecAcntValidateUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 발행사/예탁원 소각정보 소각처리대상생성
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createStoRight")
    @Operation(
        summary = "발행사/예탁원 소각정보 소각처리대상생성 API",
        description = "발행사/예탁원 소각정보 소각처리대상 정보를 생성한다.."
    )
    fun createStoRight(@RequestBody @Valid command: IsrStoRightCall): CommonResponse {
        callIsrCreateStoRightUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

}
