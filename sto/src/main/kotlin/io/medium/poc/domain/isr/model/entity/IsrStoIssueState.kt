package io.medium.poc.domain.isr.model.entity

import io.medium.poc.common.code.BalanceType
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 발행사 발행사 State
 */
@Entity
@Table(name = "ISR_STO_ISSUE_STATE")
class IsrStoIssueState(

    /** composite id */
    @EmbeddedId
    val compositeId: IsrStoIssueStateMultiKeys,

    /** 직전수량 */
    @Column(name = "POST_QTY")
    val postQty: BigDecimal? = BigDecimal.ZERO,

    /** 현재수량 */
    @Column(name = "CURRENT_QTY")
    val currentQty: BigDecimal? = BigDecimal.ZERO,

    /** 증감수량 */
    @Column(name = "IN_OUT_QTY")
    val inOutQty: BigDecimal? = BigDecimal.ZERO,

    /** 배정일시 */
    @Column(name = "ASSIGN_DTM")
    val assignDtm: LocalDateTime? = null,
)

@Embeddable
data class IsrStoIssueStateMultiKeys(
    /** 발행관리기관번호 */
    @Column(name = "ISSUE_MGMT_INST_NO", length = 5)
    val issueMgmtInstNo: String,

    /** 발행관리기관계좌번호 */
    @Column(name = "ISSUE_MGMT_INST_ACNT_NO", length = 20)
    val issueMgmtInstAcntNo: String,

    /** STO 종목번호 */
    @Column(name = "STO_ITEM_NO", length = 12)
    val stoItemNo: String,

    /** 잔고유형 */
    @Column(name = "BALANCE_TYPE", length = 2)
    @Convert(converter = BalanceType.CodeConverter::class)
    val balanceType: BalanceType,
): Serializable