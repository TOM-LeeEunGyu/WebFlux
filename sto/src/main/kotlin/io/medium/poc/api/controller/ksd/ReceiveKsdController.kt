package io.medium.poc.api.controller.ksd

import io.medium.poc.api.usecase.ksd.*
import io.medium.poc.common.code.CommonMessage
import io.medium.poc.common.model.request.TransferToKsdStoIssueAplct
import io.medium.poc.common.model.request.TransferToKsdStoIssueAssign
import io.medium.poc.common.model.request.TransferToKsdStoIssueMgmt
import io.medium.poc.common.model.request.TransferToKsdStoSbscrAplct
import io.medium.poc.common.model.response.CommonResponse
import io.medium.poc.common.utils.nowLocalDateTime
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 예탁원 write 컨트롤러
 */
@Hidden
@RestController
@RequestMapping("/api/poc/ksd")
class ReceiveKsdController(
    private val writeKsdIssueAplctUseCase: WriteKsdIssueAplctUseCase,
    private val writeKsdIssueMgmtUseCase: WriteKsdIssueMgmtUseCase,
    private val writeKsdIssueAssignUseCase: WriteKsdIssueAssignUseCase,
    private val writeKsdStoSbscrAplctUseCase: WriteKsdStoSbscrAplctUseCase,
) {

    /**  =================== 발행사로부터 전송된 정보를 처리하는 endpoint =================== **/

    /**
     * 발행사로부터 넘어온 정보를 예탁원 DB에 생성한다.
     */
    @Hidden
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/receiveAplctFromIsr")
    fun receiveAplctFromIsr(@RequestBody command: TransferToKsdStoIssueAplct): CommonResponse {
        try {
            writeKsdIssueAplctUseCase.execute(command)
        } catch (e: Exception) {
            return CommonResponse(
                code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = CommonMessage.FAIL.code,
                timestamp = nowLocalDateTime()
            )
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 발행사로부터 넘어온 투자신청 정보를를 예탁원 DB에 생성한다.
     */
    @Hidden
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/receiveAssignFromIsr")
    fun receiveAssignFromIsr(@RequestBody command: TransferToKsdStoIssueAssign): CommonResponse {
        try {
            writeKsdIssueAssignUseCase.execute(command)
        } catch (e: Exception) {
            return CommonResponse(
                code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = CommonMessage.FAIL.code,
                timestamp = nowLocalDateTime()
            )
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 발행사로부터 넘어온 게재정보를 예탁원 DB에 생성한다.
     */
    @Hidden
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/receiveMgmtFromIsr")
    fun receiveStoIssueMgmtFromIsr(@RequestBody command: TransferToKsdStoIssueMgmt): CommonResponse {
        try {
            writeKsdIssueMgmtUseCase.execute(command)
        } catch (e: Exception) {
            return CommonResponse(
                code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = CommonMessage.FAIL.code,
                timestamp = nowLocalDateTime()
            )
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /**
     * 발행사로부터 넘어온 투자자 청약신청 정보를 예탁원 DB에 생성한다.
     */
    @Hidden
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/receiveSbscrAplctFromIsr")
    fun receiveSbscrAplctFromIsr(@RequestBody command: TransferToKsdStoSbscrAplct): CommonResponse {
        try {
            writeKsdStoSbscrAplctUseCase.execute(command)
        } catch (e: Exception) {
            return CommonResponse(
                code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = CommonMessage.FAIL.code,
                timestamp = nowLocalDateTime()
            )
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }
}
