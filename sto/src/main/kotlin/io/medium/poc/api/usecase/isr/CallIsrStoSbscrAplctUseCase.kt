package io.medium.poc.api.usecase.isr

import io.medium.poc.api.controller.isr.request.command.IsrStoSbscrAplctCommand
import io.medium.poc.common.procedure.ProceduresCallService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CallIsrStoSbscrAplctUseCase(
    private val proceduresCallService: ProceduresCallService,
) {

    @Transactional
    fun execute(command: IsrStoSbscrAplctCommand) {
        command.run {
            proceduresCallService.callProcISRKsdStoSbscrAplct(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }
    }
}
