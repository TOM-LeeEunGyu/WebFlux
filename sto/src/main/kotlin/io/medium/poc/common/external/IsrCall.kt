package io.medium.poc.common.external

import io.medium.poc.common.model.request.TransferToIsrApproveStoIssue
import io.medium.poc.common.model.request.TransferToIsrApproveStoIssueMgmt
import io.medium.poc.common.model.response.CommonResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

/**
 * 발행사 호출 interface
 */
@FeignClient(name = "isr", url = "\${feign.client.config.isr.url}")
interface IsrCall {

    @PostMapping("/receiveApproveIssueDataFromKsd")
    fun receiveApproveIssueDataFromKsd(transfer: TransferToIsrApproveStoIssue): CommonResponse

    @PostMapping("/receiveApproveIssueMgmtDataFromKsd")
    fun receiveApproveIssueMgmtDataFromKsd(transfer: TransferToIsrApproveStoIssueMgmt): CommonResponse

}
