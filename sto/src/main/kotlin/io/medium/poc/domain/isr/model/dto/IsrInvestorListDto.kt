package io.medium.poc.domain.isr.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.AccountValidateYn
import io.medium.poc.common.code.SbscrMarginAmtPayYn
import io.medium.poc.common.code.YesOrNo
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDate

/**
 * 발행사 투자자배정신청_투자자배정신청정보조회
 */
@Schema(description = "발행사 투자자배정신청_투자자배정신청정보조회")
data class IsrInvestorListDto(

    @JsonIgnore
    @Schema(hidden = true, description = "청약 모집 금액")
    val issueAmt: BigDecimal? = BigDecimal.ZERO,

    @JsonIgnore
    @Schema(description = "STO종목명")
    val stoItemName: String,

    @Schema(description = "STO종목번호")
    val stoItemNo: String,

    @Schema(description = "청약자명")
    val sbscrName: String,

    @Schema(description = "고객관리기관번호")
    val custMgmtInstNo: String,

    @Schema(description = "고객관리기관계좌번호")
    val custMgmtInstAcntNo: String,

    @Schema(description = "청약신청수량")
    val issueSbscrAplctQty: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "발행청약증거금")
    val issueSbscrMarginAmt:BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "청약배정수량")
    val sbscrAssignQty: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "청약반환금액")
    val sbscrReturnAmt:BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "청약배정일자", example = "1900-01-01")
    val sbscrAssignDt: LocalDate?,

    @JsonIgnore
    @Schema(hidden = true)
    val sbscrMarginAmtPayYn: SbscrMarginAmtPayYn?,

    @JsonIgnore
    @Schema(hidden = true)
    val acntValidateYn: AccountValidateYn?,

    @Schema(description = "예탁원청약승인여부")
    val apprYn: YesOrNo,

    @Schema(description = "예탁원배정승인여부")
    val apprYn2: YesOrNo,
) {

    @JsonProperty("sbscrMarginAmtPayYnDesc")
    @Schema(description = "청약증거금납입여부 설명")
    val sbscrMarginAmtPayYnDesc = sbscrMarginAmtPayYn?.description

    @JsonProperty("sbscrMarginAmtPayYnCode")
    @Schema(description = "청약증거금납입여부 코드")
    val sbscrMarginAmtPayYnCode = sbscrMarginAmtPayYn?.code

    @JsonProperty("acntValidateYnDesc")
    @Schema(description = "계좌유효성확인여부 설명")
    val acntValidateYnDesc = acntValidateYn?.description

    @JsonProperty("acntValidateYnCode")
    @Schema(description = "계좌유효성확인여부 코드")
    val acntValidateYnCode = acntValidateYn?.code

}
