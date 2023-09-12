package io.medium.poc.api.usecase.sec

import io.medium.poc.api.controller.sec.request.command.SecCashTokenTradingCommand
import io.medium.poc.domain.sec.service.ReadSecService
import io.medium.poc.domain.sec.service.WriteSecService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteSecCashTokenTradingUseCase(
    private val writeSecService: WriteSecService,
    private val readSecService: ReadSecService,
) {
  
    @Transactional
    fun execute(command: SecCashTokenTradingCommand) {
        val tradeOrder = readSecService.retrieveTradeOrderNumber()
        writeSecService.saveCashTokenTrading(command.toEntity(tradeOrder))
    }

}
