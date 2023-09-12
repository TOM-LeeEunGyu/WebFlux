package io.medium.poc.api.usecase.otc

import io.medium.poc.api.controller.otc.request.command.CancelOtcStoOrderBookCommand
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.exception.CallProceduresException
import io.medium.poc.common.procedure.ProceduresCallService
import io.medium.poc.domain.otc.service.ReadOtcService
import io.medium.poc.domain.otc.service.WriteOtcService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteCancelOtcStoOrderBookUseCase(
    private val writeOtcService: WriteOtcService,
    private val readOtcService: ReadOtcService,
    private val proceduresCallService: ProceduresCallService,
) {
    @Transactional
    fun execute(command: CancelOtcStoOrderBookCommand) {

        val pair = with(command) {
            proceduresCallService.callProcOTCStoOrderBook(
                custMgmtInstNo,
                custMgmtInstAcntNo,
                stoItemNo,
                orderType,
                orderQty,
                orderPrice,
                orderTotalAmt(),
                orderCancelYn,
            )
        }

        if(pair.first) {
            val otcStoOrderBook = readOtcService.getOtcStoOrderBookByOrderNo(command.orderNo)
            otcStoOrderBook.orderCancelYn = YesOrNo.Y
            writeOtcService.saveOtcStoOrderBook(otcStoOrderBook)
        } else {
            throw CallProceduresException(pair.second)
        }

    }

}
