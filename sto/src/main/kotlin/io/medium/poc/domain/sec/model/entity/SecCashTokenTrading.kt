package io.medium.poc.domain.sec.model.entity

import io.medium.poc.common.code.CashTokenTradeType
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 증권사 캐시토큰거래
 */
@Entity
@Table(name = "SEC_CASH_TOKEN_TRADING")
class SecCashTokenTrading(

    /** composite id */
    @EmbeddedId
    val compositeId: SecCashTokenTradingMultiKeys,

    /** 캐시토큰거래유형 */
    @Column(name = "CASH_TOKEN_TRD_TYPE", length = 2)
    @Convert(converter = CashTokenTradeType.CodeConverter::class)
    val cashTokenTradeType: CashTokenTradeType? = null,

    /** 거래수량 */
    @Column(name = "TRD_QTY")
    val tradeQty: BigDecimal? = BigDecimal.ZERO,

    /** 거래금액 */
    @Column(name = "TRD_AMT")
    val tradeAmount: BigDecimal? = BigDecimal.ZERO,

    /** 거래일시 */
    @Column(name = "TRD_DTM")
    val tradeDtm: LocalDateTime? = null,
)

@Embeddable
data class SecCashTokenTradingMultiKeys(
    /** 은행기관번호 */
    @Column(name = "BANK_INST_NO", length = 5)
    val bankInstNo: String,

    /** 고객관리기관번호 */
    @Column(name = "CUST_MGMT_INST_NO", length = 5)
    val custMgmtInstNo: String,

    /** 캐시토큰 ID */
    @Column(name = "CASH_ID", length = 20)
    val cashId: String,

    /** 거래일자 */
    @Column(name = "TRD_DT")
    val tradeDt: LocalDate,

    /** 거래순번 */
    @Column(name = "TRD_NO")
    val tradeOrder: Long,
): Serializable