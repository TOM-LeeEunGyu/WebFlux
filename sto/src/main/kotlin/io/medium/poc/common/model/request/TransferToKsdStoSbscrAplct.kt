package io.medium.poc.common.model.request

import io.medium.poc.common.code.InvestorIdType
import io.medium.poc.domain.isr.model.entity.IsrStoSbscrAplct
import io.medium.poc.domain.ksd.model.entity.KsdStoSbscrAplct
import io.medium.poc.domain.ksd.model.entity.KsdStoSbscrAplctMultiKeys
import java.math.BigDecimal
import java.time.LocalDate

data class TransferToKsdStoSbscrAplct(
    val issueMgmtInstNo: String,
    val issueMgmtInstAcntNo: String,
    val custMgmtInstNo: String,
    val custMgmtInstAcntNo: String,
    val stoItemNo: String,
    val sbscrName: String,
    val investId: String,
    val investorIdType: InvestorIdType,
    val issueSbscrAplctQty: BigDecimal,
    val issueSbscrMarginAmt: BigDecimal,
    val issueSbscrAplctDt: LocalDate,
) {
    fun toKsdStoSbscrAplct(): KsdStoSbscrAplct {
        val compositeId = KsdStoSbscrAplctMultiKeys(
            issueMgmtInstNo = issueMgmtInstNo,
            issueMgmtInstAcntNo = issueMgmtInstAcntNo,
            custMgmtInstNo = custMgmtInstNo,
            custMgmtInstAcntNo = custMgmtInstAcntNo,
            stoItemNo = stoItemNo,
        )
        return KsdStoSbscrAplct(
            compositeId = compositeId,
            sbscrName = sbscrName,
            investId = investId,
            investorIdType = investorIdType,
            issueSbscrAplctQty = issueSbscrAplctQty,
            issueSbscrMarginAmt = issueSbscrMarginAmt,
        )
    }

    companion object{
        fun of(entity: IsrStoSbscrAplct) = with(entity) {
            TransferToKsdStoSbscrAplct(
                issueMgmtInstNo = compositeId.issueMgmtInstNo,
                issueMgmtInstAcntNo = compositeId.issueMgmtInstAcntNo,
                custMgmtInstNo = compositeId.custMgmtInstNo,
                custMgmtInstAcntNo = compositeId.custMgmtInstAcntNo,
                stoItemNo = compositeId.stoItemNo,
                sbscrName = sbscrName!!,
                investId = invstId!!,
                investorIdType = investorIdType!!,
                issueSbscrAplctQty = issueSbscrAplctQty ?: BigDecimal.ZERO,
                issueSbscrMarginAmt = issueSbscrMarginAmt ?: BigDecimal.ZERO,
                issueSbscrAplctDt = issueSbscrAplctDt!!,
            )
        }
    }

}
