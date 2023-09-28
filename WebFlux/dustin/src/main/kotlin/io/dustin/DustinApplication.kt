package io.dustin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DustinApplication

fun main(args: Array<String>) {
	runApplication<DustinApplication>(*args)
}
