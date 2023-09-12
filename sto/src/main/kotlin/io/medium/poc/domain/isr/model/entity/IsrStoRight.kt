package io.medium.poc.domain.isr.model.entity

import io.medium.poc.common.code.IncinerationReasonType
import io.medium.poc.common.code.StoRightType
import io.medium.poc.common.code.YesOrNo
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 발행사 STO 권리내역
 */
@Entity
@Table(name = "ISR_STO_RIGHT")
class IsrStoRight(

    /** composite id */
    @EmbeddedId
    val compositeId: IsrStoRightMultiKeys,

    /** 발행관리기관번호 */
    @Column(name = "ISSUE_MGMT_INST_NO", length = 5)
    var issueMgmtInstNo: String? = null,

    /** 발행관리기관계좌번호 */
    @Column(name = "ISSUE_MGMT_INST_ACNT_NO", length = 20)
    var issueMgmtInstAcntNo: String? = null,

    /** 고객관리기관번호 */
    @Column(name = "CUST_MGMT_INST_NO", length = 5)
    var custMgmtInstNo: String? = null,

    /** 고객관리기관계좌번호 */
    @Column(name = "CUST_MGMT_INST_ACNT_NO", length = 20)
    var custMgmtInstAcntNo: String? = null,

    /** STO 권리구분 */
    @Column(name = "STO_RIGHT_TYPE", length = 2)
    @Convert(converter = StoRightType.CodeConverter::class)
    var stoRightType: StoRightType? = null,

    /** STO권리대상수량 */
    @Column(name = "STO_RIGHT_OBJ_QTY")
    var stoRightObjQty: BigDecimal? = BigDecimal.ZERO,

    /** STO권리수량 */
    @Column(name = "STO_RIGHT_QTY")
    var stoRightQty: BigDecimal? = BigDecimal.ZERO,

    /** STO권리금액 */
    @Column(name = "STO_RIGHT_AMT")
    var stoRightAmount: BigDecimal? = BigDecimal.ZERO,

    /** STO 권리비율 */
    @Column(name = "STO_RIGHT_RTO")
    var stoRightRatio: BigDecimal? = BigDecimal.ZERO,

    /** 소각사유구분 */
    @Column(name = "INCINERATION_REASON_TYPE", length = 2)
    @Convert(converter = IncinerationReasonType.CodeConverter::class)
    var incinerationReasonType: IncinerationReasonType? = null,

    /** 송신여부 */
    @Column(name = "SND_YN", length = 1)
    var sndYn: YesOrNo? = YesOrNo.N,
)

@Embeddable
data class IsrStoRightMultiKeys(
    /** STO 종목번호 */
    @Column(name = "STO_ITEM_NO", length = 12)
    val stoItemNo: String,

    /** 권리등록일자 */
    @Column(name = "RIGHT_REG_DT")
    val rightRegDt: LocalDateTime,

    /** 권리등록순번 */
    @Column(name = "RIGHT_REG_NO")
    val rightRegNo: Long,

    /** 권리기준일자 */
    @Column(name = "RIGHT_BASE_DT")
    val rightBaseDt: LocalDateTime,
): Serializable