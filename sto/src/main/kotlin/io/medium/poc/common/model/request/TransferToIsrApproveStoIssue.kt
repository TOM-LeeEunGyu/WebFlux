package io.medium.poc.common.model.request

import io.medium.poc.common.code.YesOrNo
import io.medium.poc.domain.isr.model.entity.IsrStoIssueAplctMultiKeys
import java.time.LocalDate

/**
 * 예탁원에서 발행사로 공모승인 정보를 보내는 객체
 */
data class TransferToIsrApproveStoIssue(
    /** 발행관리기관번호 */
    val issueMgmtInstNo: String,
    /** 발행관리기관계좌번호 */
    val issueMgmtInstAcntNo: String,
    /** 발행인번호 */
    val issueNo: String,
    /** STO 종목번호 */
    val stoItemNo: String?,
    /** 발행승인일자 */
    val issueApprDt: LocalDate,
    /** 승인 여부 */
    val apprYn: YesOrNo,
) {
    fun compositeId(): IsrStoIssueAplctMultiKeys {
        return IsrStoIssueAplctMultiKeys(
            issueMgmtInstNo = issueMgmtInstNo,
            issueMgmtInstAcntNo = issueMgmtInstAcntNo,
            issueNo = issueNo,
        )
    }
}
