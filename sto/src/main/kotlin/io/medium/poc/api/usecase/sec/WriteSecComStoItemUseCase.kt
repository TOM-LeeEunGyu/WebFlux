package io.medium.poc.api.usecase.sec

import io.medium.poc.api.controller.sec.request.command.SecIncinerationCommand
import io.medium.poc.common.procedure.ProceduresCallService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteSecComStoItemUseCase(
    private val proceduresCallService: ProceduresCallService,
) {
    @Transactional
    fun execute(command: SecIncinerationCommand) {
        command.run {
            proceduresCallService.callProcSECSecCustAcntBalanceINCNR(stoItemNo, custMgmtInstNo)
        }
    }

}
