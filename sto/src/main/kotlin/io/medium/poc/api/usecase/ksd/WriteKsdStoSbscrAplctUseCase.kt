package io.medium.poc.api.usecase.ksd

import io.medium.poc.common.model.request.TransferToKsdStoSbscrAplct
import io.medium.poc.domain.ksd.service.WriteKsdService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteKsdStoSbscrAplctUseCase(
    private val writeKsdService: WriteKsdService,
) {

    @Transactional
    fun execute(command: TransferToKsdStoSbscrAplct) {
        command.run {
            writeKsdService.saveKsdStoSbscrAplct(toKsdStoSbscrAplct())
        }
    }

}
