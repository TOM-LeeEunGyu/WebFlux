package io.medium.poc.api.usecase.isr

import io.medium.poc.api.controller.isr.request.command.IsrStoIssueRegistrationCommand
import io.medium.poc.common.exception.FeignClientErrorException
import io.medium.poc.common.external.KsdCall
import io.medium.poc.common.model.request.TransferToKsdStoIssueAplct
import io.medium.poc.domain.isr.service.ReadIsrService
import io.medium.poc.domain.isr.service.WriteIsrService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteIsrRegistrationUseCase(
    private val writeIsrService: WriteIsrService,
    private val ksdCall: KsdCall,
    private val readIsrService: ReadIsrService,
) {
    /** 발행사 공모 등록  */
    @Transactional
    fun execute(command: IsrStoIssueRegistrationCommand) {
        val issueNo = readIsrService.isrRetrieveIssueNo()

        val isrStoIssueAplct = with(command) {
            writeIsrService.saveIsrStoIssueAplct(toIsrStoIssueAplctEntity(issueNo))
        }

        val isrStoIssue = with(command) {
            writeIsrService.saveIsrStoIssue(toIsrStoIssueEntity(issueNo))
        }

        val transferData = TransferToKsdStoIssueAplct.of(isrStoIssueAplct, isrStoIssue)

        try {
            val callResponse = ksdCall.receiveStoIssueAplctFromIsr(transferData)
            if(!callResponse.isSuccess()) {
                throw FeignClientErrorException("예탁원으로 공모신청 승인 데이터 전달중 장애가 발생했습니다.")
            }
        } catch (e: Exception) {
            throw FeignClientErrorException(e.message)
        }
    }

}
