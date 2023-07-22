package io.dustin.apps.board

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CodeTest {

    @Test
    @DisplayName("enum 테스트")
    fun enum_TEST() {

        val code = "02"

        val enumValues = TestEnum.values()
        println(enumValues)

    }

}

enum class TestEnum(
    val code: String,
) {
    ONE("01"),
    TWO("02")
}