package io.medium.poc.api.usecase.ksd

import io.medium.poc.common.model.request.TransferToKsdStoIssueAssign
import io.medium.poc.domain.ksd.service.WriteKsdService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteKsdIssueAssignUseCase(
    private val writeKsdService: WriteKsdService,
) {

    @Transactional
    fun execute(command: TransferToKsdStoIssueAssign) {
        command.run {
            writeKsdService.saveKsdStoSbscrAssign(toKsdStoSbscrAssign())
        }
    }

}
