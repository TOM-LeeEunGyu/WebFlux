package io.medium.poc

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling
import java.util.*

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
class IBKStoPocApplication

@PostConstruct
fun started() {
	TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
}

fun main(args: Array<String>) {
	runApplication<IBKStoPocApplication>(*args)
}
