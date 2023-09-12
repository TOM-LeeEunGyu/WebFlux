package io.medium.poc.domain.otc.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

data class OctAssetDto(
    @Schema(description = "예수금")
    val depositAmt: BigDecimal,

    @Schema(description = "매매증거금")
    val trdMrgnAmt: BigDecimal,

    @Schema(description = "다음 페이지 호출시 필요한 bookmark 정보")
    val bookmark: Long,

    @Schema(description = "페이징 마지막 여부")
    val last: Boolean,

    @Schema(description = "현재 검색 결과 수")
    val recordsCount: Long,

    @Schema(description = "데이터 리스트")
    val data: List<OctAssetInfoListDto>,
)