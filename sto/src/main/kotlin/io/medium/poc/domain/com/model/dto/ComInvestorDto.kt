package io.medium.poc.domain.com.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.InvestorIdType
import io.medium.poc.domain.com.model.entity.ComInvestor
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "투자자 정보 응답 객체")
data class ComInvestorDto(
    @Schema(description = "투자자 ID")
    val investorId: String?,
    @Schema(description = "투자자명")
    val investorName: String?,
    @Schema(description = "고객관리기관번호")
    val custMgmtInstNo: String?,
    @Schema(description = "고객관리기관계좌번호")
    val custMgmtInstAcntNo: String?,
    @JsonIgnore
    @Schema(hidden = true)
    val investorIdType: InvestorIdType?,
    @Schema(description = "투자자식별정보")
    val investId: String?,
) {

    @JsonProperty("investorIdTypeDesc")
    @Schema(description = "투자자식별정보구분 설명")
    val investorIdTypeDesc = investorIdType?.description

    @JsonProperty("investorIdTypeCode")
    @Schema(description = "투자자식별정보구분 코드")
    val investorIdTypeCode = investorIdType?.code

    companion object {
        fun toDto(entity: ComInvestor) = with(entity) {
            ComInvestorDto(
                investorId = investorId,
                investorName = investorName,
                custMgmtInstNo = custMgmtInstNo,
                custMgmtInstAcntNo = custMgmtInstAcntNo,
                investorIdType = investorIdType,
                investId = investId,
            )
        }
    }
}
