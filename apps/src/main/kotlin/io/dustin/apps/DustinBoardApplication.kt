package io.dustin.apps

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DustinBoardApplication

fun main(args: Array<String>) {
	runApplication<DustinBoardApplication>(*args)
}
