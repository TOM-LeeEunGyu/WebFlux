package io.medium.poc.common.model.request

import io.medium.poc.common.code.YesOrNo
import io.medium.poc.domain.isr.model.entity.IsrStoSbscrAssign
import io.medium.poc.domain.ksd.model.entity.KsdStoSbscrAssign
import io.medium.poc.domain.ksd.model.entity.KsdStoSbscrAssignMultiKeys
import java.math.BigDecimal
import java.time.LocalDate

/**
 * 발행사에서 예탁원으로 정보를 보내는 리퀘스트 객체
 */
data class TransferToKsdStoIssueAssign(
    /** 청약신청수량 */
    var sbscrAplctQty: BigDecimal? = BigDecimal.ZERO,

    /** 청약배정수량 */
    var sbscrAssignQty: BigDecimal? = BigDecimal.ZERO,

    /** 청약금액 */
    var sbscrAmt: BigDecimal? = BigDecimal.ZERO,

    /** 청약반환금액 */
    var sbscrReturnAmt: BigDecimal? = BigDecimal.ZERO,

    /** 청약배정일자 */
    var sbscrAssignDt: LocalDate? = null,

    /** 매매가능일자 */
    var trdStartDt: LocalDate? = null,

    /** 송신여부 */
    var sndYn: YesOrNo? = YesOrNo.N,

    /** 수신여부 */
    var recYn: YesOrNo? = YesOrNo.N,

    /** 승인여부 */
    var apprYn: YesOrNo? = YesOrNo.N,

    /** 발행관리기관번호 */
    val issueMgmtInstNo: String,

    /** 발행관리기관계좌번호 */
    val issueMgmtInstAcntNo: String,

    /** 고객관리기관번호 */
    val custMgmtInstNo: String,

    /** 고객관리기관계좌번호 */
    val custMgmtInstAcntNo: String,

    /** STO 종목번호 */
    var stoItemNo: String,
) {

    fun toKsdStoSbscrAssign(): KsdStoSbscrAssign {
        val compositeId = KsdStoSbscrAssignMultiKeys(
            issueMgmtInstNo = issueMgmtInstNo,
            issueMgmtInstAcntNo = issueMgmtInstAcntNo,
            custMgmtInstNo = custMgmtInstNo,
            custMgmtInstAcntNo = custMgmtInstAcntNo,
            stoItemNo = stoItemNo,
        )

        return KsdStoSbscrAssign(
            compositeId = compositeId,
            sbscrAplctQty = sbscrAplctQty,
            sbscrAssignQty = sbscrAssignQty,
            sbscrAmt = sbscrAmt,
            sbscrReturnAmt = sbscrReturnAmt,
            sbscrAssignDt = sbscrAssignDt,
            trdStartDt = trdStartDt,
            apprYn = YesOrNo.N,
            sndYn = YesOrNo.N,
        )
    }

    companion object {
        fun of(isrStoSbscrAssign: IsrStoSbscrAssign): TransferToKsdStoIssueAssign{
            return TransferToKsdStoIssueAssign(
                sbscrAplctQty = isrStoSbscrAssign.sbscrAplctQty,
                sbscrAssignQty = isrStoSbscrAssign.sbscrAssignQty,
                sbscrAmt = isrStoSbscrAssign.sbscrAmt,
                sbscrReturnAmt = isrStoSbscrAssign.sbscrReturnAmt,
                sbscrAssignDt = isrStoSbscrAssign.sbscrAssignDt,
                trdStartDt = isrStoSbscrAssign.trdStartDt,
                apprYn = isrStoSbscrAssign.apprYn,
                sndYn = isrStoSbscrAssign.sndYn,
                issueMgmtInstNo = isrStoSbscrAssign.compositeId.issueMgmtInstNo,
                issueMgmtInstAcntNo = isrStoSbscrAssign.compositeId.issueMgmtInstAcntNo,
                custMgmtInstNo = isrStoSbscrAssign.compositeId.custMgmtInstNo,
                custMgmtInstAcntNo = isrStoSbscrAssign.compositeId.custMgmtInstAcntNo,
                stoItemNo = isrStoSbscrAssign.compositeId.stoItemNo,
            )
        }
    }

}
