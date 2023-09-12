package io.medium.poc.domain.ksd.model.entity

import io.medium.poc.common.code.IncinerationReasonType
import io.medium.poc.common.code.StoRightType
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 예탁원 STO 권리내역
 */
@Entity
@Table(name = "KSD_STO_RIGHT")
class KsdStoRight(

    /** composite id */
    @EmbeddedId
    val compositeId: KsdStoRightMultiKeys,

    /** 권리기준일자 */
    @Column(name = "RIGHT_BASE_DT")
    val rightBaseDt: LocalDate?,

    /** 발행관리기관번호 */
    @Column(name = "ISSUE_MGMT_INST_NO", length = 5)
    val issueMgmtInstNo: String? = null,

    /** 발행관리기관계좌번호 */
    @Column(name = "ISSUE_MGMT_INST_ACNT_NO", length = 20)
    val issueMgmtInstAcntNo: String? = null,

    /** 고객관리기관번호 */
    @Column(name = "CUST_MGMT_INST_NO", length = 5)
    val custMgmtInstNo: String? = null,

    /** 고객관리기관계좌번호 */
    @Column(name = "CUST_MGMT_INST_ACNT_NO", length = 20)
    val custMgmtInstAcntNo: String? = null,

    /** STO 권리구분 */
    @Column(name = "STO_RIGHT_TYPE", length = 2)
    @Convert(converter = StoRightType.CodeConverter::class)
    val stoRightType: StoRightType? = null,

    /** STO 권리대상수량 */
    @Column(name = "STO_RIGHT_OBJ_QTY")
    val stoRightObjQty: BigDecimal? = BigDecimal.ZERO,

    /** STO 권리수량 */
    @Column(name = "STO_RIGHT_QTY")
    val stoRightQty: BigDecimal? = BigDecimal.ZERO,

    /** STO 권리금액 */
    @Column(name = "STO_RIGHT_AMT")
    val stoRightAmt: BigDecimal? = BigDecimal.ZERO,

    /** STO 권리비율 */
    @Column(name = "STO_RIGHT_RTO")
    val stoRightRatio: BigDecimal? = BigDecimal.ZERO,

    /** 소각사유구분 */
    @Column(name = "INCINERATION_REASON_TYPE", length = 2)
    @Convert(converter = IncinerationReasonType.CodeConverter::class)
    val incinerationReasonType: IncinerationReasonType? = null,
)

@Embeddable
data class KsdStoRightMultiKeys(
    /** STO 종목번호 */
    @Column(name = "STO_ITEM_NO", length = 12)
    val stoItemNo: String,

    /** 권리등록일자 */
    @Column(name = "RIGHT_REG_DT")
    val rightRegDt: LocalDateTime,

    /** 권리등록순번 */
    @Column(name = "RIGHT_REG_NO")
    val rightRegNo: Long,
): Serializable