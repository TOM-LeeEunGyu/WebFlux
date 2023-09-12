package io.medium.poc.api.controller.procedure.request.command

import io.medium.poc.common.code.IncinerationReasonType
import io.medium.poc.common.code.StoRightType
import io.medium.poc.common.constraint.EnumCheck
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "발행사 STO 권리 내역(소각) 생성 프로시져 요청 객체")
data class IsrStoRightCall(
    @Schema(description = "STO 종목번호")
    val stoItemNo: String,

    @Schema(description = "권리 내역 등록 날짜")
    val rightBaseDt: LocalDate,

    @Schema(description = "STO 권리구분")
    @field:EnumCheck(enumClazz = StoRightType::class, message = "STO 권리구분 필드는 01, 02, 03만 가능합니다.")
    val stoRightType: String,

    @Schema(description = "STO 권리비율")
    val stoRightRto: Int,

    @Schema(description = "소각사유구분")
    @field:EnumCheck(enumClazz = IncinerationReasonType::class, message = "소각사유구분 필드는 01, 02, 03만 가능합니다.")
    val incinerationReasonType: String,
) {
    fun stoRightType(): StoRightType {
        return StoRightType.fromCode(stoRightType)
    }

    fun incinerationReasonType(): IncinerationReasonType {
        return IncinerationReasonType.fromCode(incinerationReasonType)
    }
}
