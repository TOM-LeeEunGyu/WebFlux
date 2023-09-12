package io.medium.poc.domain.isr.model.entity

import io.medium.poc.common.code.AccountValidateYn
import io.medium.poc.common.code.InvestorIdType
import io.medium.poc.common.code.SbscrMarginAmtPayYn
import io.medium.poc.common.code.YesOrNo
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

/**
 * 발행사 STO 청약신청내역
 */
@Entity
@Table(name = "ISR_STO_SBSCR_APLCT")
class IsrStoSbscrAplct(

    /** composite id */
    @EmbeddedId
    val compositeId: IsrStoSbscrAplctAplctMultiKeys,

    /** 청약자명 */
    @Column(name = "SBSCR_NAME", length = 50)
    var sbscrName: String? = null,

    /** 발행청약신청수량 */
    @Column(name = "ISSUE_SBSCR_APLCT_QTY")
    var issueSbscrAplctQty: BigDecimal? = BigDecimal.ZERO,

    /** 발행청약증거금 */
    @Column(name = "ISSUE_SBSCR_MARGIN_AMT")
    var issueSbscrMarginAmt: BigDecimal? = BigDecimal.ZERO,

    /** 발행청약신청일자 */
    @Column(name = "ISSUE_SBSCR_APLCT_DT")
    var issueSbscrAplctDt: LocalDate? = null,

    /** 청약증거금납입확인여부 */
    @Column(name = "SBSCR_MARGIN_AMT_PAY_YN", length = 1)
    @Convert(converter = SbscrMarginAmtPayYn.CodeConverter::class)
    var sbscrMarginAmtPayYn: SbscrMarginAmtPayYn? = null,

    /** 계좌유효성확인여부 */
    @Column(name = "ACNT_VALIDATE_YN", length = 1)
    @Convert(converter = AccountValidateYn.CodeConverter::class)
    var acntValidateYn: AccountValidateYn? = null,

    /** 투자자식별정보 */
    @Column(name = "INVST_ID", length = 13)
    var invstId: String? = null,

    /** 투자자식별정보구분 */
    @Column(name = "INVST_ID_TYPE", length = 2)
    @Convert(converter = InvestorIdType.CodeConverter::class)
    var investorIdType: InvestorIdType? = null,

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
data class IsrStoSbscrAplctAplctMultiKeys(
    /** 발행관리기관번호 */
    @Column(name = "ISSUE_MGMT_INST_NO", length = 5)
    val issueMgmtInstNo: String,

    /** 발행관리기관계좌번호 */
    @Column(name = "ISSUE_MGMT_INST_ACNT_NO", length = 20)
    val issueMgmtInstAcntNo: String,

    /** 고객관리기관번호 */
    @Column(name = "CUST_MGMT_INST_NO", length = 5)
    val custMgmtInstNo: String,

    /** 고객관리기관계좌번호 */
    @Column(name = "CUST_MGMT_INST_ACNT_NO", length = 20)
    val custMgmtInstAcntNo: String,

    /** STO 종목번호 */
    @Column(name = "STO_ITEM_NO", length = 12)
    var stoItemNo: String,
): Serializable