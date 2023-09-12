package io.medium.poc.domain.otc.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

data class OctAssetInfoDto(
    @Schema(description = "예수금")
    val depositAmt: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "매매증거금")
    val trdMrgnAmt: BigDecimal? = BigDecimal.ZERO,
)