package io.medium.poc.domain.ksd.model.entity

import io.medium.poc.common.code.InvestorIdType
import jakarta.persistence.*
import java.math.BigDecimal

/**
 * 예탁원 STO 투자자한도
 */
@Entity
@Table(name = "KSD_STO_INVESTOR_LMT")
class KsdStoInvestorLmt(

    /** composite id */
    @EmbeddedId
    val compositeId: KsdStoInvestorLmtMultiKeys,

    /** 투자자한도 */
    @Column(name = "INVST_LMT")
    val investorLimit: BigDecimal? = BigDecimal.ZERO,

    /** 투자자잔여한도 */
    @Column(name = "INVST_REMAIN_LMT")
    val investorRemainLimit: BigDecimal? = BigDecimal.ZERO,

)

@Embeddable
data class KsdStoInvestorLmtMultiKeys(
    /** 투자자식별정보 */
    @Column(name = "INVST_ID", length = 13)
    val investorId: String,

    /** 투자자식별정보구분 */
    @Column(name = "INVST_ID_TYPE", length = 2)
    @Convert(converter = InvestorIdType.CodeConverter::class)
    val investorIdType: InvestorIdType,
)