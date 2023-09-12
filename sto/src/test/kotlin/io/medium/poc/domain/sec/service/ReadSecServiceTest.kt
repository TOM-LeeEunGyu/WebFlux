package io.medium.poc.domain.sec.service

import io.medium.poc.common.code.BalanceType
import io.medium.poc.domain.sec.model.entity.SecCashTokenTradingMultiKeys
import io.medium.poc.domain.sec.model.entity.SecCustMgmtInstCashTokenStateMultiKeys
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest
class ReadSecServiceTest @Autowired constructor(
	private val readSecService: ReadSecService,
) {

	@Test
	@DisplayName("custMgmtInstCashTokenStateById 테스트")
	fun custMgmtInstCashTokenStateById_TEST() {

		// given
		val compositeId = SecCustMgmtInstCashTokenStateMultiKeys(
			custMgmtInstNo = "CMIM",
			cashId = "CASHID",
			balanceType = BalanceType.CASH,
		)

		// when
		val result = readSecService.custMgmtInstCashTokenStateById(compositeId)

		// then
		assertThat(result.currentQty).isEqualTo(BigDecimal.valueOf(111))
	}

	@Test
	@DisplayName("secCashTokenTradingList 테스트")
	fun secCashTokenTradingList_TEST() {

		// given
		val compositeId = SecCashTokenTradingMultiKeys(
			bankInstNo = "SINO",
			custMgmtInstNo = "SMINO",
			cashId = "CASHID",
			tradeDt = LocalDate.now(),
			tradeOrder = 0,
		)

		val start = LocalDate.now().minusMonths(1)
		val end = LocalDate.now().plusMonths(1)

		// when
		val list = readSecService.cashTokenTradingList(compositeId, start, end)

		// then
		assertThat(list.isNotEmpty()).isEqualTo(true)
	}

	@Test
	@DisplayName("retrieveTradeOrderNumber 테스트")
	fun retrieveTradeOrderNumber_TEST() {

		// when
		val order = readSecService.retrieveTradeOrderNumber()

		// then
		assertThat(order).isGreaterThan(0)
	}

	@Test
	@DisplayName("getSecAccountValidateList 테스트")
	fun getSecAccountValidateList_TEST() {

		// when
		val list = readSecService.getSecAccountValidateList(LocalDate.now(), 10, 1)

		// then
		assertThat(list.isNotEmpty()).isEqualTo(true)
	}

}
