package io.medium.poc.domain.isr.model.dto

import java.math.BigDecimal

/**
 * 발행사 청약 신청 정보
 */
data class IsrInvestorInfoDto(
    val issueSbscrPersonCnt: Long? = 0,
    val issueAmt: BigDecimal? = BigDecimal.ZERO,
    val issueSbscrMarginAmt: BigDecimal? = BigDecimal.ZERO,
    val sbscrReturnAmt: BigDecimal? = BigDecimal.ZERO,
)