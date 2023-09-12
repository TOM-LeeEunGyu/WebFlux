package io.medium.poc.api.controller.isr

import io.medium.poc.api.usecase.isr.WriteIsrReceiveStoIssueApprUseCase
import io.medium.poc.api.usecase.isr.WriteIsrReceiveStoIssueMgmtApprUseCase
import io.medium.poc.common.code.CommonMessage
import io.medium.poc.common.model.request.TransferToIsrApproveStoIssue
import io.medium.poc.common.model.request.TransferToIsrApproveStoIssueMgmt
import io.medium.poc.common.model.response.CommonResponse
import io.medium.poc.common.utils.nowLocalDateTime
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 발행사 write 컨트롤러
 */
@Hidden
@RestController
@RequestMapping("/api/poc/isr")
class ReceiveIsrController(
    private val writeIsrReceiveStoIssueApprUseCase: WriteIsrReceiveStoIssueApprUseCase,
    private val writeIsrReceiveStoIssueMgmtApprUseCase: WriteIsrReceiveStoIssueMgmtApprUseCase,
) {
    /**  =================== 예탁원으로부터 전송된 정보를 처리하는 endpoint =================== **/

    /**
     * 예탁원 -> 발행사 공모 승인 정보 업데이트
     */
    @Hidden
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/receiveApproveIssueDataFromKsd")
    fun receiveApproveIssueDataFromKsd(@RequestBody transfer: TransferToIsrApproveStoIssue): CommonResponse {
        try {
            writeIsrReceiveStoIssueApprUseCase.execute(transfer)
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
     * 예탁원 -> 발행사 게제 승인 정보 업데이트
     */
    @Hidden
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/receiveApproveIssueMgmtDataFromKsd")
    fun receiveApproveIssueMgmtDataFromKsd(@RequestBody transfer: TransferToIsrApproveStoIssueMgmt): CommonResponse {
        try {
            writeIsrReceiveStoIssueMgmtApprUseCase.execute(transfer)
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
