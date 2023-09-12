package io.medium.poc.domain.sec.model.entity

import io.medium.poc.common.code.InvestorIdType
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal

/**
 * 증권사 고객계좌
 */
@Entity
@Table(name = "SEC_CUST_ACNT")
class SecCustAccount(

    /** composite id */
    @EmbeddedId
    val compositeId: SecCustAccountMultiKeys,

    /** 계좌명 */
    @Column(name = "ACNT_NAME", length = 50)
    val accountName: String? = null,

    /** 예수금 */
    @Column(name = "DEPOSIT_AMT")
    val depositAmount: BigDecimal? = BigDecimal.ZERO,

    /** 매매증거금 */
    @Column(name = "TRD_MRGN_AMT")
    val trdMrgnAmt: BigDecimal? = BigDecimal.ZERO,

    /** 투자자식별정보 */
    @Column(name = "INVST_ID", length = 13)
    val investorId: String? = null,

    /** 투자자식별정보구분 */
    @Column(name = "INVST_ID_TYPE", length = 2)
    @Convert(converter = InvestorIdType.CodeConverter::class)
    val investorIdType: InvestorIdType? = null,
)

@Embeddable
data class SecCustAccountMultiKeys(
    /** 고객관리기관번호 */
    @Column(name = "CUST_MGMT_INST_NO", length = 5)
    val custMgmtInstNo: String,

    /** 고객관리기관계좌번호 */
    @Column(name = "CUST_MGMT_INST_ACNT_NO", length = 20)
    val custMgmtInstAcntNo: String,
): Serializable