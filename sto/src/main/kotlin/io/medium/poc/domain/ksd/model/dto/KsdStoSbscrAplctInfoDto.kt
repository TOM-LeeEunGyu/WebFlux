package io.medium.poc.domain.ksd.model.dto

import java.math.BigDecimal

/**
 * 예탁원 청약 신청 정보
 */
data class KsdStoSbscrAplctInfoDto(
    val issueSbscrPersonCnt: Long? = 0,
    val issueSbscrMarginAmt: BigDecimal? = BigDecimal.ZERO,
    val sbscrReturnAmt: BigDecimal? = BigDecimal.ZERO,
)