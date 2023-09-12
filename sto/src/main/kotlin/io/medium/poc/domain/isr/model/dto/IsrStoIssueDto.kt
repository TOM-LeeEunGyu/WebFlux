package io.medium.poc.domain.isr.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.IssueStatusType
import io.medium.poc.common.code.StoBaseAssetType
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

@Schema(description = "발행사 공모등록정보조회 응답 객체")
data class IsrStoIssueDto (
    @Schema(description = "발행인명")
    val issueName: String?,

    @Schema(description = "발행인대표자")
    val issueRprsnName: String?,

    @Schema(description = "발행인담당자")
    val issueChargeName: String?,

    @Schema(description = "발행인연락처")
    val issueContactpnt: String?,

    @Schema(description = "발행인이메일주소")
    val issueEmail: String?,

    @Schema(description = "발행인홈페이지")
    val issueHomepage: String?,

    @Schema(description = "발행인주소")
    val issueAddr: String?,

    @Schema(description = "발행인사업자등록번호")
    val issueBusiRegNo: String?,

    @Schema(description = "발행인법인등록번호")
    val issueCorpRegNo: String?,

    @Schema(description = "발행인거래기관번호")
    val issueTrdInstNo: String?,

    @Schema(description = "발행인거래기관계좌번호")
    val issueTrdAcntNo: String?,

    @Schema(description = "발행관리기관번호")
    val issueMgmtInstNo: String?,

    @Schema(description = "발행관리기관계좌번호")
    val issueMgmtInstAcntNo: String?,

    @Schema(description = "발행신청수량(토큰증권 발행량)")
    val issueApprQty: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "STO 종목명(발행종목명)")
    val stoItemName: String?,

    @Schema(description = "STO 종목번호")
    val stoItemNo: String?,

    @Schema(description = "발행상품평가금액(발행상품 가치평가금액)")
    val issueGoodsEvlAmt: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "발행가")
    val issuePrice: BigDecimal? = BigDecimal.ZERO,

    @JsonIgnore
    @Schema(hidden = true)
    val stoBaseAssetType: StoBaseAssetType?,

    @Schema(description = "발행상품평가자료(상품 가치 평가 자료)")
    val issueGoodsEvlData: String?,

    @Schema(description = "발행상품소유증빙자료(상품 소유 증빙 자료)")
    val issueGoodsOwnEvidenceData: String?,

    @JsonIgnore
    @Schema(hidden = true)
    val issueStatusType: IssueStatusType? = null,
) {
    @JsonProperty("stoBaseAssetTypeDesc")
    @Schema(description = "토큰증권 종류 설명")
    val stoBaseAssetTypeDesc = stoBaseAssetType?.description

    @JsonProperty("stoBaseAssetTypeCode")
    @Schema(description = "토큰증권 종류 코드")
    val stoBaseAssetTypeCode = stoBaseAssetType?.code

    @JsonProperty("issueStatusTypeDesc")
    @Schema(description = "발행상태구분 설명")
    val issueStatusTypeDesc = issueStatusType?.description

    @JsonProperty("issueStatusTypeCode")
    @Schema(description = "발행상태구분 코드")
    val issueStatusTypeCode = issueStatusType?.code

}
