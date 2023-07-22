package io.dustin.apps.test

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {

    @GetMapping("/test")
    fun test(): String {
        return "Hello World"
    }
}

fun main() {
    val code = "01"

    val targetEnum = TestEnum.values()
                             .firstOrNull { it.code == code } ?: TestEnum.ETC
    println(targetEnum)
}

enum class TestEnum(
    val code: String,
) {
    ONE("01"),
    TWO("02"),
    ETC("03")
}