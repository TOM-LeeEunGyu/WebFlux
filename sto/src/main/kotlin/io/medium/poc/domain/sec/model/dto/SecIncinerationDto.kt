package io.medium.poc.domain.sec.model.dto

import java.math.BigDecimal

/**
 * 증권사  토큰증권관리(소각실행) 조회
 */
data class SecIncinerationDto(
    val balanceQty: BigDecimal? = BigDecimal.ZERO,
)