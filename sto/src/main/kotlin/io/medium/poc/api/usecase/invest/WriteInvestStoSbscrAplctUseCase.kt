package io.medium.poc.api.usecase.invest

import io.medium.poc.api.controller.invest.request.command.InvestStoSbscrAplctCommand
import io.medium.poc.domain.isr.service.WriteIsrService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteInvestStoSbscrAplctUseCase(
    private val writeIsrService: WriteIsrService,
) {

    @Transactional
    fun execute(command: InvestStoSbscrAplctCommand) {
        writeIsrService.saveIsrStoSbscrAplct(command.toEntity())
    }

}
