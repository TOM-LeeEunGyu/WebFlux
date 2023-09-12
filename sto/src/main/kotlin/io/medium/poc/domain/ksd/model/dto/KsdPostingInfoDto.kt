package io.medium.poc.domain.ksd.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.common.code.YesOrNo
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDate

data class KsdPostingInfoDto(

    @Schema(description = "발행상품명")
    val stoItemName: String?,

    @Schema(description = "STO 종목번호")
    val stoItemNo: String?,

    @Schema(description = "발행 상품 가치평가금액")
    val issueGoodsEvlAmt: BigDecimal? = BigDecimal.ZERO,

    @JsonIgnore
    @Schema(hidden = true)
    val stoBaseAssetType: StoBaseAssetType?,

    @Schema(description = "발행금액")
    val issueAmt: BigDecimal?,

    @Schema(description = "발행청약신청시작일자(발행청약시작일자)")
    val issueSbscrAplctStartDt: LocalDate?,

    @Schema(description = "발행청약신청종료일자(발행청약종료일자)")
    val issueSbscrAplctEndDt: LocalDate?,

    @Schema(description = "발행청약배정일자(청약배정일자)")
    val issueSbscrAssignDt: LocalDate?,

    @Schema(description = "토큰증권 발행량")
    val issueApprQty: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "발행일자")
    val issueDt: LocalDate?,

    @Schema(description = "발행인 상호명(발행인명)")
    val issueName: String?,

    @Schema(description = "발행인 사업자 주소")
    val issueAddr: String?,

    @Schema(description = "발행인 이메일 주소")
    val issueEmail: String?,

    @Schema(description = "발행인 대표자 이름")
    val issueRprsnName: String?,

    @Schema(description = "발행인 홈페이지")
    val issueHomepage: String?,

    @Schema(description = "사업소개서 자료")
    val businessProfileData: String?,

    @Schema(description = "투자설명서 자료")
    val prospectusData: String?,

    @Schema(description = "승인여부")
    val apprYn: YesOrNo?,
) {
    @JsonProperty("stoBaseAssetTypeDesc")
    @Schema(description = "토큰증권 종류 설명")
    val stoBaseAssetTypeDesc = stoBaseAssetType?.description

    @JsonProperty("stoBaseAssetTypeCode")
    @Schema(description = "토큰증권 종류 코드")
    val stoBaseAssetTypeCode = stoBaseAssetType?.code

}
