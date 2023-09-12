package io.medium.poc.api.usecase.isr

import io.medium.poc.api.controller.isr.request.command.IsrSecAcntValidateCommand
import io.medium.poc.common.procedure.ProceduresCallService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CallIsrSecAcntValidateUseCase(
    private val proceduresCallService: ProceduresCallService,
) {

    @Transactional
    fun execute(command: IsrSecAcntValidateCommand) {
        command.run{
            proceduresCallService.callProcISRSecAcntValidate(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }
    }

}
