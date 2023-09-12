package io.medium.poc.api.usecase.isr

import io.medium.poc.api.controller.isr.request.command.IsrStoIssueMgmtCommand
import io.medium.poc.common.exception.FeignClientErrorException
import io.medium.poc.common.external.KsdCall
import io.medium.poc.common.model.request.TransferToKsdStoIssueMgmt
import io.medium.poc.domain.isr.service.WriteIsrService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteIsrStoIssueMgmtUseCase(
    private val writeIsrService: WriteIsrService,
    private val ksdCall: KsdCall,
) {

    @Transactional
    fun execute(command: IsrStoIssueMgmtCommand) {
        val isrStoIssueMgmt = with(command) {
            writeIsrService.saveIsrStoIssueMgmt(toIsrStoIssueMgmtEntity())
        }

        val transferData = TransferToKsdStoIssueMgmt.of(isrStoIssueMgmt)

        try {
            val callResponse = ksdCall.receiveStoIssueMgmtFromIsr(transferData)
            if(callResponse.code != HttpStatus.OK.value()) {
                throw FeignClientErrorException("예탁원으로 공모신청 승인 데이터 전달중 장애가 발생했습니다.")
            }
        } catch (e: Exception) {
            throw FeignClientErrorException(e.message)
        }

    }

}
