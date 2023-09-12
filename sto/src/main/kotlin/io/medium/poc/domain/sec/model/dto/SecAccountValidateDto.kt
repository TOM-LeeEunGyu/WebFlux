package io.medium.poc.domain.sec.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.AccountValidateYn
import io.medium.poc.domain.sec.model.entity.SecAccountValidate
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

/**
 * 증권사 위탁계좌 유효성 정보 dto
 */
@Schema(description = "증권사 위탁계좌 유효성 정보 응답 객체")
data class SecAccountValidateDto(
    @Schema(description = "확인순번")
    val confirmNo: Long?,
    @Schema(description = "고객관리기관번호")
    val custMgmtInstNo: String,
    @Schema(description = "고객관리기관계좌번호")
    val custMgmtInstAcntNo: String,
    @Schema(description = "청약자명")
    val sbscrName: String?,
    @Schema(type = "string", description = "요청일시", example = "1900-01-01")
    val reqDt: LocalDate?,
    @JsonIgnore
    @Schema(hidden = true)
    var acntValidateYn: AccountValidateYn?,
) {
    @JsonProperty("acntValidateYnDesc")
    @Schema(description = "계좌유효성확인여부 설명")
    val acntValidateYnDesc = acntValidateYn?.description

    @JsonProperty("acntValidateYnCode")
    @Schema(description = "계좌유효성확인여부 코드")
    val acntValidateYnCode = acntValidateYn?.code


    companion object {
        fun toDto(entity: SecAccountValidate) = with(entity) {
            SecAccountValidateDto(
                confirmNo = compositeId.confirmNo,
                custMgmtInstNo = compositeId.custMgmtInstNo,
                custMgmtInstAcntNo = compositeId.custMgmtInstAcntNo,
                sbscrName = sbscrName,
                reqDt = reqDt,
                acntValidateYn = acntValidateYn,
            )
        }
    }
}