package io.medium.poc.common.model.request

import io.medium.poc.common.code.IssueStatusType
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.domain.isr.model.entity.IsrStoIssueMgmt
import io.medium.poc.domain.ksd.model.entity.KsdStoIssueMgmt
import io.medium.poc.domain.ksd.model.entity.KsdStoIssueMgmtMultiKeys
import java.math.BigDecimal
import java.time.LocalDate

data class TransferToKsdStoIssueMgmt(

    /** STO 종목번호 */
    val stoItemNo: String? = null,

    /** 발행인번호 */
    val issueNo: String? = null,

    /** 은행기관번호 */
    val bankInstNo: String? = null,

    /** 은행계좌번호 */
    val bankAcntNo: String? = null,

    /** 발행신청일자 */
    val issueAplctDt: LocalDate? = null,

    /** 발행승인일자 */
    var issueApprDt: LocalDate? = null,

    /** 발행승인수량 */
    val issueApprQty: BigDecimal? = BigDecimal.ZERO,

    /** 발행일자 */
    val issueDt: LocalDate? = null,

    /** 발행청약신청시작일자 */
    val issueSbscrAplctStartDt: LocalDate? = null,

    /** 발행청약신청종료일자 */
    val issueSbscrAplctEndDt: LocalDate? = null,

    /** 발행청약배정일자 */
    val issueSbscrAssignDt: LocalDate? = null,

    /** 발행금액 */
    val issueAmt: BigDecimal? = BigDecimal.ZERO,

    /** 사업소개서자료 */
    val businessProfileData: String? = null,

    /** 투자설명서자료 */
    val prospectusData: String? = null,

    /** 발행상태구분 */
    val issueStatusType: IssueStatusType? = null,

    /** 승인여부 */
    var apprYn: YesOrNo? = YesOrNo.N,

    /** 송신여부 */
    var sndYn: YesOrNo? = YesOrNo.N,

    /** 발행관리기관번호 */
    val issueMgmtInstNo: String,

    /** 발행관리기관계좌번호 */
    val issueMgmtInstAcntNo: String,

    /** 발행신청순번 */
    val issueAplctNo: Long? = null,
) {
    fun toKsdStoIssueMgmt(): KsdStoIssueMgmt {
        val compositeId = KsdStoIssueMgmtMultiKeys(
            issueMgmtInstNo = issueMgmtInstNo,
            issueMgmtInstAcntNo = issueMgmtInstAcntNo,
            stoItemNo = stoItemNo,
        )
        return KsdStoIssueMgmt(
            compositeId = compositeId,
            issueNo = issueNo,
            bankInstNo = bankInstNo,
            bankAcntNo = bankAcntNo,
            issueAplctDt = issueAplctDt,
            issueApprDt = issueApprDt,
            issueApprQty = issueApprQty,
            issueDt = issueDt,
            issueSbscrAplctStartDt = issueSbscrAplctStartDt,
            issueSbscrAplctEndDt = issueSbscrAplctEndDt,
            issueSbscrAssignDt = issueSbscrAssignDt,
            issueAmt = issueAmt,
            businessProfileData = businessProfileData,
            prospectusData = prospectusData,
            issueStatusType = IssueStatusType.PUB_INFO_EVAL,
            apprYn = apprYn,
            sndYn = sndYn,
        )
    }

    companion object{
        fun of(entity: IsrStoIssueMgmt) = with(entity) {
            TransferToKsdStoIssueMgmt(
                issueNo = issueNo,
                bankInstNo = bankInstNo,
                bankAcntNo = bankAcntNo,
                issueAplctDt = issueAplctDt,
                issueApprDt = issueApprDt,
                issueApprQty = issueApprQty,
                issueDt = issueDt,
                issueSbscrAplctStartDt = issueSbscrAplctStartDt,
                issueSbscrAplctEndDt = issueSbscrAplctEndDt,
                issueSbscrAssignDt = issueSbscrAssignDt,
                issueAmt = issueAmt,
                businessProfileData = businessProfileData,
                prospectusData = prospectusData,
                issueStatusType = issueStatusType,
                apprYn = YesOrNo.N,
                sndYn = YesOrNo.N,
                issueMgmtInstNo = compositeId.issueMgmtInstNo,
                issueMgmtInstAcntNo = compositeId.issueMgmtInstAcntNo,
                stoItemNo = compositeId.stoItemNo,
            )
        }
    }

}
