package io.medium.poc.domain.invest.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.IssueStatusType
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDate

@Schema(description = "투자자 토큰증권 목록 조회 응답 객체")
data class InvestStoIssueMgmtDto(
    @Schema(description = "STO 종목번호")
    val stoItemNo: String?,

    @Schema(description = "발행상품 이름(STO 종목명)")
    val stoItemName: String?,

    @Schema(description = "발행인 상호명")
    val issueName: String?,

    @Schema(description = "발행금액")
    val issueAmt: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "발행청약시작일자")
    val issueSbscrAplctDt: LocalDate?,

    @Schema(description = "발행청약종료일자")
    val issueSbscrAplctEndDt: LocalDate?,

    @Schema(description = "청약배정일자")
    val sbscrAssignDt: LocalDate?,

    @JsonIgnore
    @Schema(hidden = true)
    val issueStatusType: IssueStatusType?,

    @Schema(description = "발행관리기관계좌번호")
    val issueMgmtInstAcntNo: String,

    @Schema(description = "발행관리기관번호")
    val issueMgmtInstNo: String,
) {

    @JsonProperty("issueStatusTypeDesc")
    @Schema(description = "현황(코드명 조회) 설명")
    val issueStatusTypeDesc = issueStatusType?.description

    @JsonProperty("issueStatusTypeCode")
    @Schema(description = "현황(코드명 조회) 코드")
    val issueStatusTypeCode = issueStatusType?.code

}
