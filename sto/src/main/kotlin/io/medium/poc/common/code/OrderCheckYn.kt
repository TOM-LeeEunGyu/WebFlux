package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 주문가능확인여부
 */
@Schema(enumAsRef = true)
enum class OrderCheckYn(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    ORDER("주문가능", "Y"),
    NOT_ORDER("주문불가", "N");

    override fun code() = code

    companion object {
        fun fromCode(code: String): OrderCheckYn = OrderCheckYn.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<OrderCheckYn>(OrderCheckYn::class.java)

}
