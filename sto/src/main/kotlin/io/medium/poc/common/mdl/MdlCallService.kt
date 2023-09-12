package io.medium.poc.common.mdl

import io.medium.poc.common.external.MdlPayCall
import io.medium.poc.common.external.request.SendTokenArgs
import io.medium.poc.common.external.request.SendTokenRequest
import io.medium.poc.common.external.request.TradeStoArgs
import io.medium.poc.common.external.request.TradeStoRequest
import io.medium.poc.common.utils.logger
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class MdlCallService(
    private val mdlPayCall: MdlPayCall,
) {

    private val log = logger<MdlCallService>()

    fun tradeSto(
        selCustMgmtInstNo: String,
        selCustMgmtInstAcntNo: String,
        buyCustMgmtInstNo: String,
        buyCustMgmtInstAcntNo: String,
        stoItemNo: String,
        trdQty: BigDecimal,
    ) {
        val args = TradeStoArgs(
            fromAuthWalletId = selCustMgmtInstAcntNo,
            fromSecComId = selCustMgmtInstNo,
            toAuthWalletId = buyCustMgmtInstAcntNo,
            toSecComId = buyCustMgmtInstNo,
            stoId = stoItemNo,
            balanceAmount = trdQty,
            remarks = "매매",
        )
        val request = TradeStoRequest(
            args = args
        )
        try {
            mdlPayCall.tradeSto(request)
        } catch (ex: Exception) {
            log.error(ex.message)
        }
    }

    fun sendToken(
        fromAuthWalletId: String,
        toAuthWalletId: String,
        tokenId: String,
        trdQty: BigDecimal,
        remarks: String,
    ) {
        val args = SendTokenArgs(
            fromAuthWalletId = fromAuthWalletId,
            toAuthWalletId = toAuthWalletId,
            tokenId = tokenId,
            balanceAmount = trdQty,
            remarks = remarks,
        )
        val request = SendTokenRequest(
            args = args
        )
        try {
            mdlPayCall.sendToken(request)
        } catch (ex: Exception) {
            log.error(ex.message)
        }
    }
}
