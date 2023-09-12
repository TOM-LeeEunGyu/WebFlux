package io.medium.poc.domain.bank.service

import io.medium.poc.domain.bank.model.entity.BankCashTokenTradingMultiKeys
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class ReadBankServiceTest @Autowired constructor(
	private val readBankService: ReadBankService,
) {

	@Test
	@DisplayName("cashTokenTradingList 테스트")
	fun cashTokenTradingList_TEST() {

		// given
		val compositeId = BankCashTokenTradingMultiKeys(
			bankInstNo = "BINO",
			custMgmtInstNo = "CMINO",
			cashId = "cashId",
			tradeDt = LocalDate.now(),
			tradeOrder = 0,
		)

		val start = LocalDate.now().minusMonths(1)
		val end = LocalDate.now().plusMonths(1)

		// when
		val list = readBankService.cashTokenTradingList(compositeId, start, end)

		// then
		assertThat(list.isNotEmpty()).isEqualTo(true)
	}

	@Test
	@DisplayName("retrieveTradeOrderNumber 테스트")
	fun retrieveTradeOrderNumber_TEST() {

		// given
		val compositeId = BankCashTokenTradingMultiKeys(
			bankInstNo = "BINO",
			custMgmtInstNo = "CMINO",
			cashId = "cashId",
			tradeDt = LocalDate.now(),
			tradeOrder = 1,
		)

		// when
		val order = readBankService.retrieveTradeOrderNumber(
			compositeId.bankInstNo,
			compositeId.custMgmtInstNo,
			compositeId.cashId,
			compositeId.tradeDt,
		)

		// then
		assertThat(order).isGreaterThan(0)
	}

}
