package io.medium.poc.api.usecase.ksd

import io.medium.poc.api.controller.procedure.request.command.KstStoSbscrAplctCall
import io.medium.poc.common.procedure.ProceduresCallService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CallKsdStoSbscrAplctUseCase(
    private val proceduresCallService: ProceduresCallService,
) {

    @Transactional
    fun execute(command: KstStoSbscrAplctCall) {
        command.run {
            proceduresCallService.callProcKSDKsdStoSbscrAplct(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }
    }

}
