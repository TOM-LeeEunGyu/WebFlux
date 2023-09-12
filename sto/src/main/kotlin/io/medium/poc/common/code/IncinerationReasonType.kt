package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 소각사유구분 코드
 */
@Schema(enumAsRef = true)
enum class IncinerationReasonType(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    DOME_LISTING("장내상장", "01"),
    BUZ_SUSPENSION("사업운영중단", "02"),
    ETC("기타", "03");

    override fun code() = code

    companion object {
        fun fromCode(code: String): IncinerationReasonType = IncinerationReasonType.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<IncinerationReasonType>(IncinerationReasonType::class.java)

}
