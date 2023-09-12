package io.medium.poc.domain.otc.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

data class OctAssetInfoListDto(

    @Schema(description = "STO종목번호")
    val stoItemNo: String,

    @Schema(description = "STO종목명")
    val stoItemName: String,

    @Schema(description = "잔고수량")
    val balanceQty: BigDecimal,

    @Schema(description = "매매증거수량")
    val trdMrgnQty: BigDecimal,
)