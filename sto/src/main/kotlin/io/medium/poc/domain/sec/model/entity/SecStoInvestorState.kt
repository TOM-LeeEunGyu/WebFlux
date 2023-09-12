package io.medium.poc.domain.sec.model.entity

import io.medium.poc.common.code.BalanceType
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 증권사 투자자 State
 */
@Entity
@Table(name = "SEC_STO_INVESTOR_STATE")
class SecStoInvestorState(

    /** composite id */
    @EmbeddedId
    val compositeId: SecStoInvestorStateMultiKeys,

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
    @Column(name = "ASSIGN_DTM")
    val assignDtm: LocalDateTime? = null,

    /** 매매가능일자 */
    @Column(name = "TRD_START_DT")
    val tradeStartDt: LocalDate? = null,
)

@Embeddable
data class SecStoInvestorStateMultiKeys(
    /** 고객관리기관번호 */
    @Column(name = "CUST_MGMT_INST_NO", length = 5)
    val custMgmtInstNo: String,

    /** 고객관리기관계좌번호 */
    @Column(name = "CUST_MGMT_INST_ACNT_NO", length = 20)
    val custMgmtInstAcntNo: String,

    /** STO 종목번호 */
    @Column(name = "STO_ITEM_NO", length = 12)
    val stoItemNo: String,

    /** 잔고유형 */
    @Column(name = "BALANCE_TYPE", length = 2)
    @Convert(converter = BalanceType.CodeConverter::class)
    val balanceType: BalanceType,
): Serializable