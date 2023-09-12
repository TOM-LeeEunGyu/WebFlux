package io.medium.poc.api.usecase.ksd

import io.medium.poc.common.model.request.TransferToKsdStoIssueAplct
import io.medium.poc.domain.ksd.service.WriteKsdService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteKsdIssueAplctUseCase(
    private val writeKsdService: WriteKsdService,
) {

    @Transactional
    fun execute(command: TransferToKsdStoIssueAplct) {
        command.run {
            writeKsdService.saveKsdStoIssue(toKsdStoIssue())
            writeKsdService.saveKsdStoIssueAplct(toKsdStoIssueAplct())
        }
    }

}
