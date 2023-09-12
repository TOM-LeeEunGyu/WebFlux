package io.medium.poc.domain.otc.model.entity

import io.medium.poc.common.code.OrderCheckYn
import io.medium.poc.common.code.OrderType
import io.medium.poc.common.code.TradingYn
import io.medium.poc.common.code.YesOrNo
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 장외거래중개업자 오더북
 */
@Entity
@DynamicUpdate
@Table(name = "OTC_STO_ORDER_BOOK")
class OtcStoOrderBook(

    /** composite id */
    @EmbeddedId
    val compositeId: OtcStoOrderBookMultiKeys,

    /** 고객관리기관번호 */
    @Column(name = "CUST_MGMT_INST_NO", length = 5)
    val custMgmtInstNo: String? = null,

    /** 고객관리기관계좌번호 */
    @Column(name = "CUST_MGMT_INST_ACNT_NO", length = 5)
    val custMgmtInstAcntNo: String? = null,

    /** 주문구분 */
    @Column(name = "ORDER_TYPE", length = 2)
    @Convert( converter = OrderType.CodeConverter::class)
    val orderType: OrderType? = null,

    /** 주문수량 */
    @Column(name = "ORDER_QTY")
    val orderQty: BigDecimal? = BigDecimal.ZERO,

    /** 주문가격 */
    @Column(name = "ORDER_PRICE")
    val orderPrice: BigDecimal? = BigDecimal.ZERO,

    /** 주문총액 */
    @Column(name = "ORDER_TOTAL_AMT")
    val orderTotalAmt: BigDecimal? = BigDecimal.ZERO,

    /** 주문일시 */
    @Column(name = "ORDER_DTM")
    val orderDtm: LocalDateTime? = null,

    /** 주문가능확인여부 */
    @Column(name = "ORDER_CK_YN", length = 1)
    @Convert(converter = OrderCheckYn.CodeConverter::class)
    var orderCkYn: OrderCheckYn? = null,

    /** 체결여부 */
    @Column(name = "TRADING_YN", length = 1)
    @Convert( converter = TradingYn.CodeConverter::class)
    var tradingYn: TradingYn? = TradingYn.NOT_TRADE,

    /** 주문취소여부 */
    @Column(name = "ORDER_CANCEL_YN", length = 1)
    var orderCancelYn: YesOrNo? = null,
)

@Embeddable
data class OtcStoOrderBookMultiKeys(
    /** 주문일자 */
    @Column(name = "ORDER_DT")
    val orderDt: LocalDate,

    /** STO 종목번호 */
    @Column(name = "STO_ITEM_NO", length = 12)
    val stoItemNo: String,

    /** 주문순번 */
    @Column(name = "ORDER_NO")
    val orderNo: Long,
): Serializable