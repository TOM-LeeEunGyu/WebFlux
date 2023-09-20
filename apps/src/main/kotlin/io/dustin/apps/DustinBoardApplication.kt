package io.dustin.apps

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan
class DustinBoardApplication

fun main(args: Array<String>) {
	runApplication<DustinBoardApplication>(*args)
}
