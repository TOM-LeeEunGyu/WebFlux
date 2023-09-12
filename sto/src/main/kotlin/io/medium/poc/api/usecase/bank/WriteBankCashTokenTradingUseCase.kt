package io.medium.poc.api.usecase.bank

import io.medium.poc.api.controller.bank.request.command.BankCashTokenTradingCommand
import io.medium.poc.domain.bank.service.ReadBankService
import io.medium.poc.domain.bank.service.WriteBankService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteBankCashTokenTradingUseCase(
    private val writeBankService: WriteBankService,
    private val readBankService: ReadBankService,
) {

    @Transactional
    fun execute(command: BankCashTokenTradingCommand) {
        val orderNumber = with(command.toCompositeId()) {
            readBankService.retrieveTradeOrderNumber(
                bankInstNo,
                custMgmtInstNo,
                cashId,
                tradeDt
            )
        }
        writeBankService.saveCashTokenTrading(command.toEntity(orderNumber))
    }

}
