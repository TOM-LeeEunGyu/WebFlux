package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 체결여부
 */
@Schema(enumAsRef = true)
enum class TradingYn(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    TRADED("체결", "Y"),
    NOT_TRADE("미체결", "N");

    override fun code() = code

    companion object {
        fun fromCode(code: String): TradingYn = TradingYn.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<TradingYn>(TradingYn::class.java)

}
