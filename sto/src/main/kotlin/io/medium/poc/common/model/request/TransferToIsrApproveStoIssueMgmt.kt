package io.medium.poc.common.model.request

import io.medium.poc.common.code.IssueStatusType
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.domain.isr.model.entity.IsrStoIssueMgmtMultiKeys
import java.time.LocalDate

/**
 * 예탁원에서 발행사로 게승인 정보를 보내는 객체
 */
data class TransferToIsrApproveStoIssueMgmt(
    /** 발행관리기관번호 */
    val issueMgmtInstNo: String,
    /** 발행관리기관계좌번호 */
    val issueMgmtInstAcntNo: String,
    /** STO 발행번호 */
    val stoItemNo: String?,
    /** 발행상태구분 */
    var issueStatusType: IssueStatusType?,
    /** 최종발행일자 */
    var lstIssueDt: LocalDate?,
    /** 발행승인일자 */
    val issueApprDt: LocalDate?,
    /** 승인 여부 */
    val apprYn: YesOrNo?,
) {
    fun compositeId(): IsrStoIssueMgmtMultiKeys {
        return IsrStoIssueMgmtMultiKeys(
            issueMgmtInstNo = issueMgmtInstNo,
            issueMgmtInstAcntNo = issueMgmtInstAcntNo,
            stoItemNo = stoItemNo,
        )
    }
}
