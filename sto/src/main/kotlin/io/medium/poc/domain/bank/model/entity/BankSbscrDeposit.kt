package io.medium.poc.domain.bank.model.entity

import io.medium.poc.common.code.YesOrNo
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 은행 청약금액입금내역
 */
@Entity
@Table(name = "BNK_SBSCR_DEPOSIT")
class BankSbscrDeposit(

    /** composite id */
    @EmbeddedId
    val compositeId: BankSbscrDepositMultiKeys,

    /** 입금액 */
    @Column(name = "DEPOSIT_AMT")
    val depositAmount: BigDecimal? = BigDecimal.ZERO,

    /** 거래일시 */
    @Column(name = "TRD_DTM")
    val tradeDtm: LocalDateTime? = null,

    /** 송신여부 */
    @Column(name = "SND_YN", length = 1)
    val sndYn: YesOrNo? = YesOrNo.N,
)

@Embeddable
data class BankSbscrDepositMultiKeys(
    /** 입금일자 */
    @Column(name = "DEPOSIT_DT", length = 8)
    val depositDt: String,

    /** 고객관리기관번호 */
    @Column(name = "CUST_MGMT_INST_NO", length = 5)
    val custMgmtInstNo: String,

    /** 고객관리기관계좌번호 */
    @Column(name = "CUST_MGMT_INST_ACNT_NO", length = 10)
    val custMgmtInstAcntNo: String,
): Serializable