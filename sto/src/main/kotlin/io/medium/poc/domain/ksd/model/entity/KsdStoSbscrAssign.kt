package io.medium.poc.domain.ksd.model.entity

import io.medium.poc.common.code.YesOrNo
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

/**
 * 예탁원 STO 청약배정내역
 */
@Entity
@Table(name = "KSD_STO_SBSCR_ASSIGN")
class KsdStoSbscrAssign(

    /** composite id */
    @EmbeddedId
    val compositeId: KsdStoSbscrAssignMultiKeys,

    /** 청약신청수량 */
    @Column(name = "SBSCR_APLCT_QTY")
    val sbscrAplctQty: BigDecimal? = BigDecimal.ZERO,

    /** 청약배정수량 */
    @Column(name = "SBSCR_ASSIGN_QTY")
    val sbscrAssignQty: BigDecimal? = BigDecimal.ZERO,

    /** 청약금액 */
    @Column(name = "SBSCR_AMT")
    val sbscrAmt: BigDecimal? = BigDecimal.ZERO,

    /** 청약반환금액 */
    @Column(name = "SBSCR_RETURN_AMT")
    val sbscrReturnAmt: BigDecimal? = BigDecimal.ZERO,

    /** 청약배정일자 */
    @Column(name = "SBSCR_ASSIGN_DT")
    val sbscrAssignDt: LocalDate? = null,

    /** 매매가능일자 */
    @Column(name = "TRD_START_DT")
    val trdStartDt: LocalDate? = null,

    /** 승인여부 */
    @Column(name = "APPR_YN", length = 1)
    var apprYn: YesOrNo? = YesOrNo.N,

    /** 송신여부 */
    @Column(name = "SND_YN", length = 1)
    var sndYn: YesOrNo? = YesOrNo.N,
)

@Embeddable
data class KsdStoSbscrAssignMultiKeys(
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
    val stoItemNo: String,
): Serializable