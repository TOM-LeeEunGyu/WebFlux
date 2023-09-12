package io.medium.poc.common.model.request

import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.domain.isr.model.entity.IsrStoIssue
import io.medium.poc.domain.isr.model.entity.IsrStoIssueAplct
import io.medium.poc.domain.ksd.model.entity.KsdStoIssue
import io.medium.poc.domain.ksd.model.entity.KsdStoIssueAplct
import io.medium.poc.domain.ksd.model.entity.KsdStoIssueAplctMultiKeys
import java.math.BigDecimal
import java.time.LocalDate

/**
 * 발행사에서 예탁원으로 정보를 보내는 리퀘스트 객체
 */
data class TransferToKsdStoIssueAplct(

    /** 발행인명 */
    val issueName: String? = null,

    /** 발행인명(단축) */
    val issueNameShort: String? = null,

    /** 발행인대표자 */
    val issueRprsnName: String? = null,

    /** 발행인담당자 */
    val issueChargeName: String? = null,

    /** 발행인연락처 */
    val issueContactpnt: String? = null,

    /** 발행인이메일주소 */
    val issueEmail: String? = null,

    /** 발행인홈페이지 */
    val issueHomepage: String? = null,

    /** 발행인주소 */
    val issueAddress: String? = null,

    /** 발행인한도 */
    val issueLimit: BigDecimal? = BigDecimal.ZERO,

    /** 발행인잔여한도 */
    val issueRemainingLimit: BigDecimal? = BigDecimal.ZERO,

    /** 발행인사업자등록번호 */
    val issueBusiRegNo: String? = null,

    /** 발행인법인등록번호 */
    val issueCorpRegNo: String? = null,

    /** 최종발행일자 */
    val lstIssueDt: LocalDate? = null,

    /** 발행인거래기관번호 */
    val issueTrdInstNo: String? = null,

    /** 발행인거래기관계좌번호 */
    val issueTrdAcntNo: String? = null,

    /** 발행신청일자 */
    val issueAplctDt: LocalDate? = null,

    /** 발행승인일자 */
    val issueApprovalDt: LocalDate? = null,

    /** 발행신청수량 */
    val issueApprQty: BigDecimal? = BigDecimal.ZERO,

    /** STO 종목번호 */
    val stoItemNo: String? = null,

    /** STO 종목명 */
    val stoItemName: String? = null,

    /** 발행상품평가금액 */
    val issueGoodsEvlAmt: BigDecimal? = BigDecimal.ZERO,

    /** 발행가 */
    val issuePrice: BigDecimal? = BigDecimal.ZERO,

    /** STO 기초자산구분 */
    val stoBaseAssetType: StoBaseAssetType? = null,

    /** 발행상품평가자료 */
    val issueGoodsEvlData: String? = null,

    /** 발행상품소유증빙자료 */
    val issueGoodsOwnEvidenceData: String? = null,

    /** 발행관리기관번호 */
    val issueMgmtInstNo: String,

    /** 발행관리기관계좌번호 */
    val issueMgmtInstAcntNo: String,

    /** 발행인번호 */
    val issueNo: String,
) {

    fun toKsdStoIssue(): KsdStoIssue {
        return KsdStoIssue(
            issueNo = issueNo,
            issueName = issueName,
            issueNameShort = issueNameShort,
            issueRprsnName = issueRprsnName,
            issueChargeName = issueChargeName,
            issueContactpnt = issueContactpnt,
            issueEmail = issueEmail,
            issueHomepage = issueHomepage,
            issueAddr = issueAddress,
            issueLmt = issueLimit,
            issueRemainLmt = issueRemainingLimit,
            issueBusiRegNo = issueBusiRegNo,
            issueCorpRegNo = issueCorpRegNo,
            lstIssueDt = lstIssueDt,
            issueTrdInstNo = issueTrdInstNo,
            issueTrdAcntNo = issueTrdAcntNo,
        )
    }

    fun toKsdStoIssueAplct(): KsdStoIssueAplct {
        val compositeId = KsdStoIssueAplctMultiKeys(
            issueMgmtInstNo = issueMgmtInstNo,
            issueMgmtInstAcntNo = issueMgmtInstAcntNo,
            issueNo = issueNo,
        )

        return KsdStoIssueAplct(
            compositeId = compositeId,
            issueAplctDt = issueAplctDt,
            issueApprDt = issueApprovalDt,
            issueApprQty = issueApprQty,
            stoItemNo = stoItemNo,
            stoItemName = stoItemName,
            issueGoodsEvlAmt = issueGoodsEvlAmt,
            issuePrice = issuePrice,
            stoBaseAssetType = stoBaseAssetType,
            issueGoodsEvlData = issueGoodsEvlData,
            issueGoodsOwnEvidenceData = issueGoodsOwnEvidenceData,
        )
    }

    companion object {
        fun of(isrStoIssueAplct: IsrStoIssueAplct, isrStoIssue: IsrStoIssue): TransferToKsdStoIssueAplct {
            return TransferToKsdStoIssueAplct(
                issueName = isrStoIssue.issueName,
                issueNameShort = isrStoIssue.issueNameShort,
                issueRprsnName = isrStoIssue.issueRprsnName,
                issueChargeName = isrStoIssue.issueChargeName,
                issueContactpnt = isrStoIssue.issueContactpnt,
                issueEmail = isrStoIssue.issueEmail,
                issueHomepage = isrStoIssue.issueHomepage,
                issueAddress = isrStoIssue.issueAddr,
                issueLimit = isrStoIssue.issueLimit,
                issueRemainingLimit = isrStoIssue.issueRemainingLimit,
                issueBusiRegNo = isrStoIssue.issueBusiRegNo,
                issueCorpRegNo = isrStoIssue.issueCorpRegNo,
                lstIssueDt = isrStoIssue.lstIssueDt,
                issueTrdInstNo = isrStoIssue.issueTrdInstNo,
                issueTrdAcntNo = isrStoIssue.issueTrdAcntNo,
                issueAplctDt = isrStoIssueAplct.issueAplctDt,
                issueApprovalDt = isrStoIssueAplct.issueApprovalDt,
                issueApprQty = isrStoIssueAplct.issueApprQty,
                stoItemNo = isrStoIssueAplct.stoItemNo,
                stoItemName = isrStoIssueAplct.stoItemName,
                issueGoodsEvlAmt = isrStoIssueAplct.issueGoodsEvlAmt,
                issuePrice = isrStoIssueAplct.issuePrice,
                stoBaseAssetType = isrStoIssueAplct.stoBaseAssetType,
                issueGoodsEvlData = isrStoIssueAplct.issueGoodsEvlData,
                issueGoodsOwnEvidenceData = isrStoIssueAplct.issueGoodsOwnEvidenceData,
                issueMgmtInstNo = isrStoIssueAplct.compositeId.issueMgmtInstNo,
                issueMgmtInstAcntNo = isrStoIssueAplct.compositeId.issueMgmtInstAcntNo,
                issueNo = isrStoIssue.issueNo,
            )
        }
    }

}
