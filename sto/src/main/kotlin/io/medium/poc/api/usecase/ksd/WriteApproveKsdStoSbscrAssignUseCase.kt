package io.medium.poc.api.usecase.ksd

import io.medium.poc.api.controller.ksd.request.command.KsdStoSbscrAssignApproveCommand
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.domain.isr.service.ReadIsrService
import io.medium.poc.domain.isr.service.WriteIsrService
import io.medium.poc.domain.ksd.service.ReadKsdService
import io.medium.poc.domain.ksd.service.WriteKsdService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteApproveKsdStoSbscrAssignUseCase(
    private val readKsdService: ReadKsdService,
    private val writeKsdService: WriteKsdService,
    private val readIsrService: ReadIsrService,
    private val writeIsrService: WriteIsrService,
) {

    @Transactional
    fun execute(command: KsdStoSbscrAssignApproveCommand) {
        val ksdStoSbscrAssignList = with(command) {
            readKsdService.fetchKsdStoSbscrAssignForApprove(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }

        ksdStoSbscrAssignList.forEach { ksdStoSbscrAssign ->
            ksdStoSbscrAssign.apprYn = command.apprYn()
            ksdStoSbscrAssign.sndYn = YesOrNo.Y
            writeKsdService.saveKsdStoSbscrAssign(ksdStoSbscrAssign)
        }

        val isrStoSbscrAssignList = with(command) {
            readIsrService.batchForIsrStoSbscrAssigns(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }

        isrStoSbscrAssignList.forEach { isrStoSbscrAssign ->
            isrStoSbscrAssign.apprYn = command.apprYn()
            isrStoSbscrAssign.recYn = YesOrNo.Y
            writeIsrService.saveIsrStoSbscrAssign(isrStoSbscrAssign)
        }


    }

}
