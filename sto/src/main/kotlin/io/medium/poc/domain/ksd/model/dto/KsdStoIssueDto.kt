package io.medium.poc.domain.ksd.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.common.code.YesOrNo
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

/**
 * 예탁원 공모신청승인 리스트 응답 객체
 */
@Schema(description = "예탁원 공모신청승인 리스트 응답 객체")
data class KsdStoIssueDto(

    @Schema(description = "발행인명")
    val issueName: String?,

    @Schema(description = "발행인대표자")
    val issueRprsnName: String?,

    @Schema(description = "발행상품 이름(STO 종목명)")
    val issueEmail: String?,

    @Schema(description = "발행상품 이름(STO 종목명)")
    val issueTrdInstNo: String?,

    @Schema(description = "발행상품 이름(STO 종목명)")
    val issueChargeName: String?,

    @Schema(description = "발행상품 이름(STO 종목명)")
    val issueAddr: String?,

    @Schema(description = "발행인사업자등록번호")
    val issueBusiRegNo: String?,

    @Schema(description = "발행인법인등록번호")
    val issueCorpRegNo: String?,

    @Schema(description = "발행인연락처")
    val issueContactpnt: String?,

    @Schema(description = "발행인거래기관계좌번호")
    val issueTrdAcntNo: String?,

    @Schema(description = "발행상품 이름(STO 종목명)")
    val stoItemName: String?,

    @Schema(description = "STO 종목번호")
    val stoItemNo: String?,

    @Schema(description = "토큰증권 발행량(발행신청수량)")
    val issueApprQty: BigDecimal? = BigDecimal.ZERO,

    @JsonIgnore
    @Schema(hidden = true)
    val stoBaseAssetType: StoBaseAssetType?,

    @Schema(description = "발행상품 가치평가금액")
    val issueGoodsEvlAmt: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "발행가")
    val issuePrice: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "입력값(승인여부)")
    val apprYn: YesOrNo?,
) {
    @JsonProperty("stoBaseAssetTypeDesc")
    @Schema(description = "토큰증권 종류 설명")
    val stoBaseAssetTypeDesc = stoBaseAssetType?.description

    @JsonProperty("stoBaseAssetTypeCode")
    @Schema(description = "토큰증권 종류 코드")
    val stoBaseAssetTypeCode = stoBaseAssetType?.code
}
