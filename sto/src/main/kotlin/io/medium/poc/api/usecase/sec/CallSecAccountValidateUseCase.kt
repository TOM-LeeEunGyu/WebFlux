package io.medium.poc.api.usecase.sec

import io.medium.poc.api.controller.sec.request.command.SecAccountValidateCommand
import io.medium.poc.api.controller.sec.request.command.SecAccountValidateInfo
import io.medium.poc.common.procedure.ProceduresCallService
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CallSecAccountValidateUseCase(
    private val proceduresCallService: ProceduresCallService,
) {

    @Transactional
    fun execute(command: SecAccountValidateCommand) {
        callValidateProcedures(command.data)
    }

    private fun callValidateProcedures(list: List<SecAccountValidateInfo>) = runBlocking {
        val channel = produce {
            list.forEach { info ->
                launch {
                    send(info)
                }
            }
        }

        launch {
            channel.consumeEach { info ->
                with(info) {
                    proceduresCallService.callProcSECIsrStoSbscrAplct(
                        custMgmtInstNo, custMgmtInstAcntNo, confirmNo, reqDt, acntValidateYn
                    )
                }
            }
        }
    }

}
