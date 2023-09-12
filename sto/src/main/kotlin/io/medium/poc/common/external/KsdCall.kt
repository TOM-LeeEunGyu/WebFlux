package io.medium.poc.common.external

import io.medium.poc.common.model.request.TransferToKsdStoIssueAplct
import io.medium.poc.common.model.request.TransferToKsdStoIssueAssign
import io.medium.poc.common.model.request.TransferToKsdStoIssueMgmt
import io.medium.poc.common.model.request.TransferToKsdStoSbscrAplct
import io.medium.poc.common.model.response.CommonResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

/**
 * 예탁원 호출 interface
 */
@FeignClient(name = "ksd", url = "\${feign.client.config.ksd.url}")
interface KsdCall {

    /**
     * 발행사가 예탁원으로 공모 신청 정보를 보낸다.
     */
    @PostMapping("/receiveAplctFromIsr")
    fun receiveStoIssueAplctFromIsr(request: TransferToKsdStoIssueAplct): CommonResponse

    /**
     * 발행사가 예탁원으로 투자신청 정보를 보낸다.
     */
    @PostMapping("/receiveAssignFromIsr")
    fun receiveAssignFromIsr(request: TransferToKsdStoIssueAssign): CommonResponse


    /**
     * 발행사가 예탁원으로 게재 정보를 보낸다.
     */
    @PostMapping("/receiveMgmtFromIsr")
    fun receiveStoIssueMgmtFromIsr(request: TransferToKsdStoIssueMgmt): CommonResponse

    /**
     * 발행사가 예탁원으로 투자자청약신청 정보를 보낸다.
     */
    @PostMapping("/receiveSbscrAplctFromIsr")
    fun receiveSbscrAplctFromIsr(request: TransferToKsdStoSbscrAplct): CommonResponse

}
