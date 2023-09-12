package io.medium.poc.api.usecase.isr

import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.model.request.TransferToIsrApproveStoIssueMgmt
import io.medium.poc.domain.isr.service.ReadIsrService
import io.medium.poc.domain.isr.service.WriteIsrService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteIsrReceiveStoIssueMgmtApprUseCase(
    private val writeIsrService: WriteIsrService,
    private val readIsrService: ReadIsrService,
) {
    @Transactional
    fun execute(transfer: TransferToIsrApproveStoIssueMgmt) {
        val isrStoIssueMgmt = readIsrService.getIsrStoIssueMgmtById(transfer.compositeId())
        isrStoIssueMgmt.issueApprDt = transfer.issueApprDt
        isrStoIssueMgmt.issueStatusType = transfer.issueStatusType
        isrStoIssueMgmt.recYn = YesOrNo.Y
        isrStoIssueMgmt.apprYn = transfer.apprYn
        writeIsrService.saveIsrStoIssueMgmt(isrStoIssueMgmt)

        val isrStoIssue = readIsrService.getIsrStoIssueById(isrStoIssueMgmt.issueNo!!)
        isrStoIssue.lstIssueDt = transfer.lstIssueDt
        writeIsrService.saveIsrStoIssue(isrStoIssue)

    }

}
