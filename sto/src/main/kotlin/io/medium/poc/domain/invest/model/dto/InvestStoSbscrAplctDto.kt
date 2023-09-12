package io.medium.poc.domain.invest.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.InvestorIdType
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

@Schema(description = "투자자 청약신청 조회 응답 객체")
data class InvestStoSbscrAplctDto(

    @Schema(description = "STO 종목번호")
    val stoItemNo: String?,

    @Schema(description = "STO 종목명")
    val stoItemName: String?,

    @Schema(description = "투자자명(청약자명)")
    val sbscrName: String?,

    @Schema(description = "투자자식별정보")
    val invstId: String?,

    @JsonIgnore
    @Schema(hidden = true)
    val invstIdType: InvestorIdType?,

    @Schema(description = "고객관리기관번호")
    val custMgmtInstNo: String?,

    @Schema(description = "고객관리기관계좌번호")
    val custMgmtInstAcntNo: String?,

    @Schema(description = "발행청약신청수량")
    val issueSbscrAplctQty: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "발행청약증거금")
    val issueSbscrMarginAmt: BigDecimal? = BigDecimal.ZERO,
) {

    @JsonProperty("invstIdTypeDesc")
    @Schema(description = "투자자식별정보구분 설명")
    val invstIdTypeDesc = invstIdType?.description

    @JsonProperty("invstIdTypeCode")
    @Schema(description = "투자자식별정보구분 코드")
    val invstIdTypeCode = invstIdType?.code

}
