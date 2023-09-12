package io.medium.poc.domain.bank.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

/**
 * 은행 발행대금 정산처리 조회 Dto
 */
@Schema(description = "은행 발행대금 정산처리 조회 응답 객체")
data class BankSettlementIssueInfoDto(
    @Schema(description = "토큰증권명")
    val stoItemName: String?,
    @Schema(description = "STO 종목번호")
    val stoItemNo: String?,
    @Schema(description = "발행인")
    val issueName: String?,
    @Schema(description = "총 청약대금")
    val issueSbscrMarginAmt: BigDecimal? = BigDecimal.ZERO,
    @Schema(description = "발행인대금")
    val issueAmt: BigDecimal? = BigDecimal.ZERO,
    @Schema(description = "잔여대금")
    val issueSbscrRemainAmt: BigDecimal? = BigDecimal.ZERO,
)