package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 소각여부
 */
@Schema(enumAsRef = true)
enum class IncinerationYn(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    INCINERATION("소각", "Y"),
    UN_INCINERATION("미소각", "N");

    override fun code() = code

    companion object {
        fun fromCode(code: String): IncinerationYn = IncinerationYn.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<IncinerationYn>(IncinerationYn::class.java)

}
