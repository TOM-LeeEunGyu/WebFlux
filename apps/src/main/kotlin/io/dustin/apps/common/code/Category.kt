package io.dustin.apps.common.code

import io.dustin.apps.common.convert.EnumConverter
import io.dustin.apps.common.convert.InterfaceGenericEnum

enum class Category(
    private val code: String,
    private val deprecation: String,
): InterfaceGenericEnum<Category> {

    Free("01", "임시"),
    Coin("02", "임시"),
    NORMAL("03", "임시");

    // Function to override the defaultIfNull() method from InterfaceGenericEnum
    override fun defaultIfNull() = NORMAL

    class LowCaseConverter : EnumConverter<Category>(Category::class.java)
}
