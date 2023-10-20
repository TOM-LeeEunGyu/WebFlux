package io.dustin.api.router.mugi.model

import io.dustin.common.constraint.EnumCheck
import io.dustin.domain.mugi.model.Mugi
import io.dustin.domain.mugi.model.code.ReleasedType
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank

data class CreateMugi(
    @field:Min(1, message = "유저 아이디가 누락되었습니다.")
    val userId: Long,
    @field:NotNull(message = "무기 이름을 지어주세요 . 최소 한 글자 이상이어야 합니다.")
    @field:NotBlank(message = "무기 이름에 빈 공백은 허용하지 않습니다.")
    val name: String,
    @field:NotNull(message = "라벨 정보가 누락되었습니다.")
    @field:NotBlank(message = "라벨에 빈 공백은 허용하지 않습니다.")
    val label: String,
    @field:NotNull(message = "무기 형태 정보가 누락되었습니다.")
    @field:EnumCheck(enumClazz = ReleasedType::class, message = "releasedType 필드는 SINGLE, JAK, GUMAE 만 가능합니다.")
    val releasedType: String,
    @field:Min(0, message = "무기 구매 or 생성일이 누락되었습니다.")
    val releasedYear: Int,
    @field:NotNull(message = "무기 형식이 누락되엇습니다.")
    @field:NotBlank(message = "무기 형식에 빈 공백은 허용하지 않습니다.")
    val format: String,
) {
    fun toEntity(): Mugi {
        return Mugi(
            userId = userId,
            name = name,
            label = label,
            releasedType = ReleasedType.valueOf(releasedType.uppercase()),
            releasedYear = releasedYear,
            format = format,
        )
    }
}