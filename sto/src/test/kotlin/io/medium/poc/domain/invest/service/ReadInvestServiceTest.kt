package io.medium.poc.domain.invest.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReadInvestServiceTest @Autowired constructor(
	private val readInvestService: ReadInvestService,
) {

	@Test
	@DisplayName("investStoIssueManagements 테스트")
	fun investStoIssueManagements_TEST() {
		// given
		val search = "IS001"

		// when
		val selected = readInvestService.investStoIssueManagements(search, 10, 0)

		// then
		assertThat(selected.isNotEmpty()).isEqualTo(true)
	}

	@Test
	@DisplayName("investStoSbscrApplication 테스트")
	fun investStoSbscrApplication_TEST() {
		// given
		val stoItemNo = "KR9999000001"
		val custMgmtInstNo = "S0225"
		val custMgmtInstAcntNo = "022599010123456789"

		// when
		val selected = readInvestService.investStoSbscrApplication(
			stoItemNo,
			custMgmtInstNo,
			custMgmtInstAcntNo
		)

		// then
		assertThat(selected.stoItemNo).isEqualTo(stoItemNo)

	}

}
