package io.medium.poc.api.usecase.otc

import io.medium.poc.api.controller.otc.request.command.OtcEnforcementResultCommand
import io.medium.poc.common.mdl.MdlCallService
import io.medium.poc.common.procedure.ProceduresCallService
import org.springframework.stereotype.Service

@Service
class WriteOtcStoTradingUseCase(
    private val proceduresCallService: ProceduresCallService,
    private val mdlCallService: MdlCallService,
) {

    fun execute(command: OtcEnforcementResultCommand) {
        command.run {
            proceduresCallService.callProcOTCStoTrading(
                selCustMgmtInstNo,
                selCustMgmtInstAcntNo,
                buyCustMgmtInstNo,
                buyCustMgmtInstAcntNo,
                stoItemNo,
                trdType,
                trdQty,
                trdAmt,
                orderNo,
            )

            mdlCallService.tradeSto(
                selCustMgmtInstNo,
                selCustMgmtInstAcntNo,
                buyCustMgmtInstNo,
                buyCustMgmtInstAcntNo,
                stoItemNo,
                trdQty
            )
        }
    }

}
