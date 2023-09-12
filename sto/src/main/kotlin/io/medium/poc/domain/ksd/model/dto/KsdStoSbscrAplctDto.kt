package io.medium.poc.domain.ksd.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

/**
 * 예탁원 투자자 배정승인 목록 조회 응답 객체
 */
@Schema(description = "예탁원 투자자 배정승인 목록 조회 응답 객체")
data class KsdStoSbscrAplctDto(

    @Schema(description = "청약 신청 인원")
    val issueSbscrPersonCnt: Long? = 0,

    @Schema(description = "청약 모집 금액")
    val sbscrAmt: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "청약 신청 금액")
    val issueSbscrMarginAmt: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "청약 반환 금액")
    val sbscrReturnAmt: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "다음 페이지 호출시 필요한 bookmark 정보")
    val bookmark: Long,

    @Schema(description = "페이징 마지막 여부")
    val last: Boolean,

    @Schema(description = "현재 검색 결과 수")
    val recordsCount: Long,

    @Schema(description = "데이터 리스트")
    val data: List<KsdStoSbscrAplctListDto>,
)