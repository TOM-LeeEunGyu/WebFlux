package io.medium.poc.domain.com.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ComStoItemServiceTest @Autowired constructor(
    private val read: ReadComStoItemService,
) {

	@Test
	@DisplayName("checkExistsComStoItem 테스트")
	fun checkExistsComStoItem_TEST() {
		// given
		val stoItemNo = "KR9999000001"

		// when
		val isExist = read.checkExistsComStoItem(stoItemNo)

		// then
		assertThat(isExist).isEqualTo(true)
	}

}
