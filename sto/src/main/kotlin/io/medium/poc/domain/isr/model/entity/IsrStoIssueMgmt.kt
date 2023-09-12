package io.medium.poc.domain.isr.model.entity

import io.medium.poc.common.code.IssueStatusType
import io.medium.poc.common.code.YesOrNo
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

/**
 * 발행사 STO 발행관리정보
 */
@Entity
@Table(name = "ISR_STO_ISSUE_MGMT")
data class IsrStoIssueMgmt(

    /** composite id */
    @EmbeddedId
    val compositeId: IsrStoIssueMgmtMultiKeys,

    /** 발행인번호 */
    @Column(name = "ISSUE_NO", length = 10)
    var issueNo: String? = null,

    /** 은행기관번호 */
    @Column(name = "BANK_INST_NO", length = 5)
    var bankInstNo: String? = null,

    /** 은행계좌번호 */
    @Column(name = "BANK_ACNT_NO", length = 20)
    var bankAcntNo: String? = null,

    /** 발행신청일자 */
    @Column(name = "ISSUE_APLCT_DT")
    var issueAplctDt: LocalDate? = null,

    /** 발행승인일자 */
    @Column(name = "ISSUE_APPR_DT")
    var issueApprDt: LocalDate? = null,

    /** 발행승인수량 */
    @Column(name = "ISSUE_APPR_QTY")
    var issueApprQty: BigDecimal? = BigDecimal.ZERO,

    /** 발행일자 */
    @Column(name = "ISSUE_DT")
    var issueDt: LocalDate? = null,

    /** 발행청약신청시작일자 */
    @Column(name = "ISSUE_SBSCR_APLCT_START_DT")
    var issueSbscrAplctStartDt: LocalDate? = null,

    /** 발행청약신청종료일자 */
    @Column(name = "ISSUE_SBSCR_APLCT_END_DT")
    var issueSbscrAplctEndDt: LocalDate? = null,

    /** 발행청약배정일자 */
    @Column(name = "ISSUE_SBSCR_ASSIGN_DT")
    var issueSbscrAssignDt: LocalDate? = null,

    /** 발행금액 */
    @Column(name = "ISSUE_AMT")
    var issueAmt: BigDecimal? = BigDecimal.ZERO,

    /** 사업소개서자료 */
    @Column(name = "BUSINESS_PROFILE_DATA", length = 300)
    var businessProfileData: String? = null,

    /** 투자설명서자료 */
    @Column(name = "PROSPECTUS_DATA", length = 300)
    var prospectusData: String? = null,

    /** 발행상태구분 */
    @Column(name = "ISSUE_STATUS_TYPE", length = 2)
    @Convert(converter = IssueStatusType.CodeConverter::class)
    var issueStatusType: IssueStatusType? = null,

    /** 송신여부 */
    @Column(name = "SND_YN", length = 1)
    var sndYn: YesOrNo? = YesOrNo.N,

    /** 수신여부 */
    @Column(name = "REC_YN", length = 1)
    var recYn: YesOrNo? = YesOrNo.N,

    /** 승인여부 */
    @Column(name = "APPR_YN", length = 1)
    var apprYn: YesOrNo? = YesOrNo.N,
)

@Embeddable
data class IsrStoIssueMgmtMultiKeys(
    /** 발행관리기관번호 */
    @Column(name = "ISSUE_MGMT_INST_NO", length = 5)
    val issueMgmtInstNo: String,

    /** 발행관리기관계좌번호 */
    @Column(name = "ISSUE_MGMT_INST_ACNT_NO", length = 20)
    val issueMgmtInstAcntNo: String,

    /** STO 종목번호 */
    @Column(name = "STO_ITEM_NO", length = 12)
    var stoItemNo: String? = null,
): Serializable