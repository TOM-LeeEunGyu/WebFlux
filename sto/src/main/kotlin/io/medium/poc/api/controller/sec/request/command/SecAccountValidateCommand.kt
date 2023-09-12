package io.medium.poc.api.controller.sec.request.command

import com.fasterxml.jackson.annotation.JsonFormat
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.constraint.EnumCheck
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Schema(description = "증권사 위탁계좌 유효성 확인 요청 객체")
data class SecAccountValidateCommand(
    val data: List<SecAccountValidateInfo>
)

data class SecAccountValidateInfo(
    @Schema(description = "고객관리기관번호")
    @field:NotNull(message = "고객관리기관번호 정보는 필수입니다.")
    @field:NotBlank(message = "고객관리기관번호 정보는 필수입니다.")
    val custMgmtInstNo: String,

    @Schema(description = "고객관리기관계좌번호")
    @field:NotNull(message = "고객관리기관계좌번호 정보는 필수입니다.")
    @field:NotBlank(message = "고객관리기관계좌번호 정보는 필수입니다.")
    val custMgmtInstAcntNo: String,

    @Schema(description = "확인순번")
    @field:NotNull(message = "확인순번 정보는 필수입니다.")
    val confirmNo: Long,

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", description = "계좌유효성확인요청일자", example = "1900-01-01")
    val reqDt: LocalDate,

    @Schema(description = "입력값(승인여부)")
    @field:EnumCheck(enumClazz = YesOrNo::class, message = "유효성확인 필드는 Y, N 만 가능합니다.")
    val acntValidateYn: String,
)