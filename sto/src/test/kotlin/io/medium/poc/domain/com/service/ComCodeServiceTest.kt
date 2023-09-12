package io.medium.poc.domain.com.service

import io.medium.poc.domain.com.model.entity.ComCodeMultiKeys
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ComCodeServiceTest @Autowired constructor(
    private val readComCodeService: ReadComCodeService,
) {

	@Test
	@DisplayName("find by id 테스트")
	fun findById_TEST() {
		// given
		val type = "A001"
		val code = "B0003"

		val ids = ComCodeMultiKeys(type, code)

		// when
		val selected = readComCodeService.findById(ids)

		// then
		assertThat(selected.compositeId.comType).isEqualTo(type)
	}

}
