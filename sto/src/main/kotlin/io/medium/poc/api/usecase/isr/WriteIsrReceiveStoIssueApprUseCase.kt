package io.medium.poc.api.usecase.isr

import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.model.request.TransferToIsrApproveStoIssue
import io.medium.poc.domain.isr.service.ReadIsrService
import io.medium.poc.domain.isr.service.WriteIsrService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteIsrReceiveStoIssueApprUseCase(
    private val writeIsrService: WriteIsrService,
    private val readIsrService: ReadIsrService,
) {
    @Transactional
    fun execute(transfer: TransferToIsrApproveStoIssue) {
        val isrStoIssue = readIsrService.getIsrStoIssueById(transfer.issueNo)
        isrStoIssue.apprYn = transfer.apprYn
        isrStoIssue.recYn = YesOrNo.Y
        val isrStoIssueAplct = readIsrService.getIsrStoIssueAplctById(transfer.compositeId())
        isrStoIssueAplct.stoItemNo = transfer.stoItemNo
        isrStoIssueAplct.issueApprovalDt = transfer.issueApprDt
        isrStoIssueAplct.recYn = YesOrNo.Y
        isrStoIssueAplct.apprYn = transfer.apprYn
        writeIsrService.saveIsrStoIssue(isrStoIssue)
        writeIsrService.saveIsrStoIssueAplct(isrStoIssueAplct)
    }
}
