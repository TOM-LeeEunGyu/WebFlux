package io.medium.poc.api.usecase.ksd

import io.medium.poc.common.model.request.TransferToKsdStoIssueMgmt
import io.medium.poc.domain.ksd.service.WriteKsdService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteKsdIssueMgmtUseCase(
    private val writeKsdService: WriteKsdService,
) {
    @Transactional
    fun execute(command: TransferToKsdStoIssueMgmt) {
        command.run {
            writeKsdService.saveKsdStoIssueMgmt(toKsdStoIssueMgmt())
        }
    }
}
