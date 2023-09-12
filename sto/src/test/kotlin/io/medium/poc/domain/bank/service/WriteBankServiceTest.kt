package io.medium.poc.domain.bank.service

import io.medium.poc.common.code.CashTokenTradeType
import io.medium.poc.domain.bank.model.entity.BankCashTokenTrading
import io.medium.poc.domain.bank.model.entity.BankCashTokenTradingMultiKeys
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest
class WriteBankServiceTest @Autowired constructor(
	private val writeBankService: WriteBankService,
) {

	@Test
	@DisplayName("saveCashTokenTrading 테스트")
	fun saveCashTokenTrading_TEST() {
		// given

		val compositeId = BankCashTokenTradingMultiKeys(
			bankInstNo = "BINO",
			custMgmtInstNo = "CMINO",
			cashId = "cashId",
			tradeDt = LocalDate.now(),
			tradeOrder = 1,
		)

		val entity = BankCashTokenTrading(
			compositeId = compositeId,
			cashTokenTradeType = CashTokenTradeType.SELL,
			tradeQty = BigDecimal(10),
			tradeAmount = BigDecimal(10),
		)

		writeBankService.saveCashTokenTrading(entity)
	}

}
