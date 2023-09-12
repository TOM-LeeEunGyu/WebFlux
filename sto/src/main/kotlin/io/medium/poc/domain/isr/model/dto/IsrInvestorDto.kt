package io.medium.poc.domain.isr.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

/**
 * 발행사 투자자배정신청_투자자배정신청정보조회
 */
@Schema(description = "발행사 투자자배정신청_투자자배정신청정보조회")
data class IsrInvestorDto(
    @Schema(description = "발행관리기관번호")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호")
    val issueMgmtInstAcntNo: String,

    @Schema(description = "STO종목번호")
    val stoItemNo: String,

    @Schema(description = "STO종목명")
    val stoItemName: String? = null,

    // COUNT(ISR_STO_SBSCR_APLCT)
    @Schema(description = "청약 신청 인원")
    val issueSbscrPersonCnt: Long? = 0,

    @Schema(description = "청약모집금액")
    val sbscrAmt: BigDecimal? = BigDecimal.ZERO,

    // SUM(ISR_STO_SBSCR_APLCT->ISSUE_SBSCR_MARGIN_AMT)
    @Schema(description = "청약신청금액")
    val issueSbscrMarginAmt: BigDecimal? = BigDecimal.ZERO,

    // SUM(ISR_STO_SBSCR_ASSIGN->SBSCR_RETURN_AMT)
    @Schema(description = "청약 반환 금액")
    val sbscrReturnAmt: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "다음 페이지 호출시 필요한 bookmark 정보")
    val bookmark: Long,

    @Schema(description = "페이징 마지막 여부")
    val last: Boolean,

    @Schema(description = "현재 검색 결과 수")
    val recordsCount: Long,

    @Schema(description = "데이터 리스트")
    val data: List<IsrInvestorListDto>,
)