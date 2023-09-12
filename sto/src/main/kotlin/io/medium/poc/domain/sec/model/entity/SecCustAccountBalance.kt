package io.medium.poc.domain.sec.model.entity

import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal

/**
 * 증권사 고객계좌잔고
 */
@Entity
@Table(name = "SEC_CUST_ACNT_BALANCE")
class SecCustAccountBalance(

    /** composite id */
    @EmbeddedId
    val compositeId: SecCustAccountBalanceMultiKeys,

    /** 잔고수량 */
    @Column(name = "BALANCE_QTY")
    val balanceQty: BigDecimal? = BigDecimal.ZERO,

    /** 매매증거수량 */
    @Column(name = "TRD_MRGN_QTY")
    val trdMrgnQty: BigDecimal? = BigDecimal.ZERO,
)

@Embeddable
data class SecCustAccountBalanceMultiKeys(
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