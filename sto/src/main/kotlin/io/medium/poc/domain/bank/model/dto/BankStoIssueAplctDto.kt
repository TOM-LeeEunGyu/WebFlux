package io.medium.poc.domain.bank.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.IssueStatusType
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDate

/**
 * 예탁원 토큰증권목록조회 리스트 응답 객체
 */
@Schema(description = "은행 토큰증권목록조회 리스트 응답 객체")
data class BankStoIssueAplctDto(
    @Hidden
    @JsonIgnore
    val issueNo: String,
    @Schema(description = "발행상품 이름(STO 종목명)")
    val stoItemName: String?,
    @Schema(description = "STO 종목번호")
    val stoItemNo: String?,
    @Schema(description = "발행신청수량")
    val issueProposalQty: BigDecimal? = BigDecimal.ZERO,
    @Schema(description = "발행승인수량")
    val issueApproveQty: BigDecimal? = BigDecimal.ZERO,
    @Schema(description = "발행인 상호명(발행인명)")
    val issueName: String?,
    @Schema(description = "발행신청일자", example = "1900-01-01")
    val issueAplctDt: LocalDate?,
    @Schema(description = "최종발행일자", example = "1900-01-01")
    val lstIssueDt: LocalDate?,
    @JsonIgnore
    @Schema(hidden = true)
    val issueStateType: IssueStatusType?,
) {
    @JsonProperty("issueStateTypeDesc")
    @Schema(description = "현황 설명")
    fun issueStateTypeDesc(): String {
        return issueStateType?.description ?: IssueStatusType.UNDER_REV_APP.description
    }

    @JsonProperty("issueStateTypeCode")
    @Schema(description = "현황 코드")
    fun issueStateTypeCode(): String {
        return issueStateType?.code ?: IssueStatusType.UNDER_REV_APP.code
    }

}
