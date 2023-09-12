package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 매매가능여부
 */
@Schema(enumAsRef = true)
enum class TradeStartYn(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    TRADE_ABLE("매매가능", "Y"),
    NOT_TRADE("매매불가", "N");

    override fun code() = code

    companion object {
        fun fromCode(code: String): TradeStartYn = TradeStartYn.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<TradeStartYn>(TradeStartYn::class.java)

}
