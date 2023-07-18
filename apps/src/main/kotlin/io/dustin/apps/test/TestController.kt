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