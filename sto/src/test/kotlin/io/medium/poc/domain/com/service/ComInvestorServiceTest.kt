package io.medium.poc.domain.com.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ComInvestorServiceTest @Autowired constructor(
    private val readComInvestorService: ReadComInvestorService,
) {

	@Test
	@DisplayName("test by id 테스트")
	fun testById_TEST() {
		// given
		val id = "INV00001"

		// when
		val selected = readComInvestorService.comInvestors()

		// then
		assertThat(selected.isNotEmpty()).isEqualTo(true)
	}

}
