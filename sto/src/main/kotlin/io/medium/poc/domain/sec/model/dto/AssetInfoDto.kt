package io.medium.poc.domain.sec.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

/**
 * 증권사 고객 보유자산 정보 조회 응답 객체
 */
@Schema(description = "증권사 고객 보유자산 정보 조회")
data class AssetInfoDto(
    @Schema(description = "예수금")
    val depositAmt: BigDecimal,
    @Schema(description = "매매증거금")
    val trdMrgnAmt: BigDecimal,
    @Schema(description = "STO종목번호")
    val stoItemNo: String,
    @Schema(description = "STO종목명")
    val stoItemName: String,
    @Schema(description = "잔고수량")
    val balanceQty: BigDecimal,
    @Schema(description = "매매증거수량")
    val trdMrgnQty: BigDecimal,
)