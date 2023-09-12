package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 계좌유효성확인여부
 */
@Schema(enumAsRef = true)
enum class AccountValidateYn(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    CHECKED("확인", "Y"),
    UNCHECKED("미확인", "N");

    override fun code() = code

    companion object {
        fun fromCode(code: String): AccountValidateYn = AccountValidateYn.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<AccountValidateYn>(AccountValidateYn::class.java)

}
