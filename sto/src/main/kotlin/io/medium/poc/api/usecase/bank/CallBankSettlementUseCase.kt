package io.medium.poc.api.usecase.bank

import io.medium.poc.api.controller.bank.request.command.BankSettlementCommand
import io.medium.poc.common.procedure.ProceduresCallService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CallBankSettlementUseCase(
    private val proceduresCallService: ProceduresCallService,
) {

    @Transactional
    fun execute(command: BankSettlementCommand) {
        proceduresCallService.callProcBNKSecCustAcnt(command.stoItemNo)
    }

}
