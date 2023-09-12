package io.medium.poc.domain.ksd.model.entity

import io.medium.poc.common.code.TradeType
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 예탁원 매매거래(체결)
 */
@Entity
@Table(name = "KSD_STO_TRADING")
class KsdStoTrading(

    /** composite id */
    @EmbeddedId
    val compositeId: KsdStoTradingMultiKeys,

    /** 거래유형 */
    @Column(name = "TRD_TYPE", length = 2)
    @Convert(converter = TradeType.CodeConverter::class)
    val tradeType: TradeType? = null,

    /** 거래수량 */
    @Column(name = "TRD_QTY")
    val tradeQty: BigDecimal? = BigDecimal.ZERO,

    /** 거래일시 */
    @Column(name = "TRD_DTM")
    val tradeDtm: LocalDateTime? = null,
)

@Embeddable
data class KsdStoTradingMultiKeys(
    /** 매도고객관리기관번호 */
    @Column(name = "SEL_CUST_MGMT_INST_NO", length = 5)
    val selCustMgmtInstNo: String,

    /** 매도고객관리기관계좌번호 */
    @Column(name = "SEL_CUST_MGMT_INST_ACNT_NO", length = 20)
    val selCustMgmtInstAcntNo: String,

    /** 매수고객관리기관번호 */
    @Column(name = "BUY_CUST_MGMT_INST_NO", length = 5)
    val buyCustMgmtInstNo: String,

    /** 매수고객관리기관계좌번호 */
    @Column(name = "BUY_CUST_MGMT_INST_ACNT_NO", length = 20)
    val buyCustMgmtInstAcntNo: String,

    /** STO 종목번호 */
    @Column(name = "STO_ITEM_NO", length = 12)
    val stoItemNo: String,
): Serializable