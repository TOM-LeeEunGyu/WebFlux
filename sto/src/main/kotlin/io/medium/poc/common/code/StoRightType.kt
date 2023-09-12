package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * STO 권리구분 코드
 */
@Schema(enumAsRef = true)
enum class StoRightType(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    ALLOCATION("배당", "01"),
    APPRECIATION("감자", "02"),
    INCINERATION("소각", "03");

    override fun code() = code

    companion object {
        fun fromCode(code: String): StoRightType = StoRightType.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<StoRightType>(StoRightType::class.java)

}
