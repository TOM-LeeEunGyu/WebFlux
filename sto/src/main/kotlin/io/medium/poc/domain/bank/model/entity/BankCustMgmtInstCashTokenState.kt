package io.medium.poc.domain.bank.model.entity

import io.medium.poc.common.code.BalanceType
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 은행 고객관리기관 캐시토큰 State
 */
@Entity
@Table(name = "BNK_CUST_MGMT_INST_CASH_TOKEN_STATE")
class BankCustMgmtInstCashTokenState(

    /** composite id */
    @EmbeddedId
    val compositeId: BankCustMgmtInstCashTokenStateMultiKeys,

    /** 직전수량 */
    @Column(name = "POST_QTY")
    val postQty: BigDecimal? = BigDecimal.ZERO,

    /** 현재수량 */
    @Column(name = "CURRENT_QTY")
    val currentQty: BigDecimal? = BigDecimal.ZERO,

    /** 증감수량 */
    @Column(name = "IN_OUT_QTY")
    val inOutQty: BigDecimal? = BigDecimal.ZERO,

    /** 변동일시 */
    @Column(name = "UPD_DTM")
    val updateDtm: LocalDateTime? = null,
)

@Embeddable
data class BankCustMgmtInstCashTokenStateMultiKeys(
    /** 고객관리기관번호 */
    @Column(name = "CUST_MGMT_INST_NO", length = 5)
    val custMgmtInstNo: String,

    /** 캐시토큰 ID */
    @Column(name = "CASH_ID", length = 20)
    val cashId: String,

    /** 잔고유형 */
    @Column(name = "BALANCE_TYPE", length = 2)
    @Convert(converter = BalanceType.CodeConverter::class)
    val balanceType: BalanceType,
): Serializable