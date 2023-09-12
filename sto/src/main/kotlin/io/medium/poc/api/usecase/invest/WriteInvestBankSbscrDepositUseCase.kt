package io.medium.poc.api.usecase.invest

import io.medium.poc.api.controller.invest.request.command.InvestBankSbscrDepositCommand
import io.medium.poc.domain.bank.service.WriteBankService
import org.springframework.stereotype.Service

@Service
class WriteInvestBankSbscrDepositUseCase(
    private val writeBankService: WriteBankService,
) {

    fun execute(command: InvestBankSbscrDepositCommand) {
        writeBankService.saveBankSbscrDeposit(command.toEntity())
    }

}
