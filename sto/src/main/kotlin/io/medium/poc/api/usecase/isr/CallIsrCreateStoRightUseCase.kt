package io.medium.poc.api.usecase.isr

import io.medium.poc.api.controller.procedure.request.command.IsrStoRightCall
import io.medium.poc.common.procedure.ProceduresCallService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CallIsrCreateStoRightUseCase(
    private val proceduresCallService: ProceduresCallService,
) {

    @Transactional
    fun execute(command: IsrStoRightCall) {
        command.run {
            proceduresCallService.callProcISRCreateStoRight(
                stoItemNo, rightBaseDt, stoRightType(), stoRightRto, incinerationReasonType()
            )
        }
    }
}
