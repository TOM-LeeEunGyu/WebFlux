package io.medium.poc.domain.sec.model.entity

import io.medium.poc.common.code.OrderCheckYn
import io.medium.poc.common.code.OrderType
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 증권사 고객계좌잔고
 */
@Entity
@Table(name = "SEC_STO_ORDER")
class SecStoOrder(

    /** composite id */
    @EmbeddedId
    val compositeId: SecStoOrderMultiKeys,

    /** 주문구분 */
    @Column(name = "ORDER_TYPE", length = 2)
    @Convert(converter = OrderType.CodeConverter::class)
    val orderType: OrderType? = null,

    /** 주문수량 */
    @Column(name = "ORDER_QTY")
    val orderQty: BigDecimal? = BigDecimal.ZERO,

    /** 주문가격 */
    @Column(name = "ORDER_PRICE")
    val orderPrice: BigDecimal? = BigDecimal.ZERO,

    /** 주문총액 */
    @Column(name = "ORDER_TOTAL_AMT")
    val orderTotalAmount: BigDecimal? = BigDecimal.ZERO,

    /** 주문일시 */
    @Column(name = "ORDER_DTM")
    val orderDtm: LocalDateTime? = null,

    /** 주문가능확인여부 */
    @Column(name = "ORDER_CK_YN", length = 1)
    @Convert(converter = OrderCheckYn.CodeConverter::class)
    val orderCheckYn: OrderCheckYn? = null,
)

@Embeddable
data class SecStoOrderMultiKeys(
    /** 고객관리기관번호 */
    @Column(name = "CUST_MGMT_INST_NO", length = 5)
    val custMgmtInstNo: String,

    /** 고객관리기관계좌번호 */
    @Column(name = "CUST_MGMT_INST_ACNT_NO", length = 20)
    val custMgmtInstAcntNo: String,

    /** STO 종목번호 */
    @Column(name = "STO_ITEM_NO", length = 12)
    val stoItemNo: String,

    /** 주문순번 */
    @Column(name = "ORDER_NO", length = 10)
    val orderNo: String,
): Serializable