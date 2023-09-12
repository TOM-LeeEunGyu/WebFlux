package io.medium.poc.domain.isr.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.common.code.YesOrNo
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDate

/**
 * 발행사 게재정보접소_게재정보조회 응답 객체
 */
@Schema(description = "발행사 게재정보접소_게재정보조회 응답 객체")
data class IsrPubInfoDto(
    @Schema(description = "STO 종목명(발행상품명)")
    val stoItemName: String,

    @Schema(description = "STO 종목번호")
    val stoItemNo: String,

    @Schema(description = "발행상품평가금액(발행 상품 가치평가금액)")
    val issueGoodsEvlAmt: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "발행신청수량(토큰증권 발행량)")
    val issueApprQty: BigDecimal? = BigDecimal.ZERO,

    @JsonIgnore
    @Schema(hidden = true)
    val stoBaseAssetType: StoBaseAssetType?,

    @Schema(description = "발행금액")
    val issueAmt: BigDecimal? = BigDecimal.ZERO,

    @Schema(type = "string", description = "발행청약신청시작일자(발행청약시작일자)", example = "1900-01-01")
    val issueSbscrAplctStartDt: LocalDate?,

    @Schema(type = "string", description = "발행청약신청종료일자(발행청약종료일자)", example = "1900-01-01")
    val issueSbscrAplctEndDt: LocalDate?,

    @Schema(type = "string", description = "발행청약배정일자(청약배정일자)", example = "1900-01-01")
    val issueSbscrAssignDt: LocalDate?,

    @Schema(description = "발행일자")
    val issueDt: LocalDate?,

    @Schema(description = "은행기관번호")
    val bankInstNo: String,

    @Schema(description = "은행계좌번호")
    val bankAcntNo: String,

    @Schema(description = "발행인명(발행인 상호명)")
    val issueName: String?,

    @Schema(description = "발행인주소(발행인 사업자 주소)")
    val issueAddr: String?,

    @Schema(description = "발행인 이메일 주소")
    val issueEmail: String?,

    @Schema(description = "발행인대표자(발행인 대표자 이름)")
    val issueRprsnName: String?,

    @Schema(description = "발행인홈페이지(발행인 홈페이지 링크)")
    val issueHomepage: String?,

    @Schema(description = "사업소개서 자료")
    val businessProfileData: String?,

    @Schema(description = "투자설명서 자료")
    val prospectusData: String?,

    @Schema(description = "송신여부")
    val sndYn: YesOrNo?,

    @Schema(description = "수신여부")
    val recYn: YesOrNo?,

    @Schema(description = "승인여부")
    val apprYn: YesOrNo?,
) {
    @JsonProperty("stoBaseAssetTypeDesc")
    @Schema(description = "STO 기초자산구분(토큰증권 종류) 설명")
    val stoBaseAssetTypeDesc = stoBaseAssetType?.description

    @JsonProperty("stoBaseAssetTypeCode")
    @Schema(description = "STO 기초자산구분(토큰증권 종류) 코드")
    val stoBaseAssetTypeCode = stoBaseAssetType?.code
}
