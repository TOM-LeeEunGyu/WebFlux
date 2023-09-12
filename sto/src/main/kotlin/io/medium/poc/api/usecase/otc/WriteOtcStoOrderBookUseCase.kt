package io.medium.poc.api.usecase.otc

import io.medium.poc.api.controller.otc.request.command.OtcStoOrderBookCommand
import io.medium.poc.common.code.OrderCheckYn
import io.medium.poc.common.exception.CallProceduresException
import io.medium.poc.common.procedure.ProceduresCallService
import io.medium.poc.domain.otc.service.ReadOtcService
import io.medium.poc.domain.otc.service.WriteOtcService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteOtcStoOrderBookUseCase(
    private val writeOtcService: WriteOtcService,
    private val readOtcService: ReadOtcService,
    private val proceduresCallService: ProceduresCallService,
) {
    @Transactional
    fun execute(command: OtcStoOrderBookCommand) {

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
            val otcStoOrderBookNo:Long = readOtcService.otcStoOrderBookNo()
            command.run {
                val otcStoOrderBook = toOtcStoOrderBookEntity(otcStoOrderBookNo)
                otcStoOrderBook.orderCkYn = OrderCheckYn.ORDER
                writeOtcService.saveOtcStoOrderBook(toOtcStoOrderBookEntity(otcStoOrderBookNo))
            }
        } else {
            throw CallProceduresException(pair.second)
        }

    }

}
