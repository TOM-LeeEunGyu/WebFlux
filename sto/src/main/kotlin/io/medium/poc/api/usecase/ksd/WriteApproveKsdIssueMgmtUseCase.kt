package io.medium.poc.api.usecase.ksd

import io.medium.poc.api.controller.ksd.request.command.KsdStoIssueMgmtApproveCommand
import io.medium.poc.common.code.IssueStatusType
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.utils.notFoundEntity
import io.medium.poc.common.utils.nowLocalDate
import io.medium.poc.domain.ksd.service.ReadKsdService
import io.medium.poc.domain.ksd.service.WriteKsdService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class WriteApproveKsdIssueMgmtUseCase(
    private val readKsdService: ReadKsdService,
    private val writeKsdService: WriteKsdService,
) {
    @Transactional
    fun execute(command: KsdStoIssueMgmtApproveCommand) {
        val ksdStoIssueMgmt = with(command) {
            readKsdService.fetchKsdStoIssueMgmtForApprove(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }

        val ksdStoIssue = ksdStoIssueMgmt.issueNo?. let {
            readKsdService.fetchKsdStoIssueById(it)
        } ?: notFoundEntity("발행인 번호 정보가 없습니다.")


        ksdStoIssueMgmt.apprYn = command.apprYn()
        if(command.apprYn == YesOrNo.Y.name) {
            ksdStoIssueMgmt.issueStatusType = IssueStatusType.ONGOING_SUB
            ksdStoIssue.lstIssueDt = LocalDate.now()
        } else {
            ksdStoIssueMgmt.issueStatusType = IssueStatusType.PUB_REJECT
        }
        ksdStoIssueMgmt.issueApprDt = nowLocalDate()
        writeKsdService.saveKsdStoIssueMgmt(ksdStoIssueMgmt)
        writeKsdService.saveKsdStoIssue(ksdStoIssue)
    }

}
