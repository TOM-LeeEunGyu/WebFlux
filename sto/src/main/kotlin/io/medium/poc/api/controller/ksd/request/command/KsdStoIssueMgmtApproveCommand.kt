package io.medium.poc.api.controller.ksd.request.command

import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.constraint.EnumCheck
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Schema(description = "예탁원 게제정보 승인 요청 객체")
data class KsdStoIssueMgmtApproveCommand(
    @Schema(description = "발행관리기관번호")
    @field:NotNull(message = "발행관리기관번호는 필수입니다.")
    @field:NotBlank(message = "발행관리기관번호는 필수입니다.")
    @field:Size(max = 5, message = "발행관리기관번호 길이는 5를 넘을 수 없습니다.")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호")
    @field:NotNull(message = "발행관리기관계좌번호는 필수입니다.")
    @field:NotBlank(message = "발행관리기관계좌번호는 필수입니다.")
    @field:Size(max = 20, message = "발행관리기관계좌번호 길이는 20을 넘을 수 없습니다.")
    val issueMgmtInstAcntNo: String,

    @Schema(description = "STO 종목번호")
    @field:NotNull(message = "STO 종목번호는 필수입니다.")
    @field:NotBlank(message = "STO 종목번호는 필수입니다.")
    @field:Size(max = 12, message = "STO 종목번호 길이는 12를 넘을 수 없습니다.")
    val stoItemNo: String,

    @Schema(description = "입력값(승인여부)")
    @field:EnumCheck(enumClazz = YesOrNo::class, message = "입력값(승인여부) 필드는 Y, N 만 가능합니다.")
    val apprYn: String,
) {
    fun apprYn(): YesOrNo {
        return YesOrNo.valueOf(apprYn)
    }
}
