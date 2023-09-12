package io.medium.poc.domain.sec.service

import io.medium.poc.common.code.CashTokenTradeType
import io.medium.poc.domain.sec.model.entity.SecCashTokenTrading
import io.medium.poc.domain.sec.model.entity.SecCashTokenTradingMultiKeys
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest
class WriteSecServiceTest @Autowired constructor(
	private val writeSecService: WriteSecService,
) {

	@Test
	@DisplayName("secCashTokenTradingRepository 테스트")
	fun secCashTokenTradingRepository_TEST() {
		// given

		val compositeId = SecCashTokenTradingMultiKeys(
			bankInstNo = "SINO",
			custMgmtInstNo = "SMINO",
			cashId = "CASHID",
			tradeDt = LocalDate.now(),
			tradeOrder = 1,
		)

		val entity = SecCashTokenTrading(
			compositeId = compositeId,
			cashTokenTradeType = CashTokenTradeType.SELL,
			tradeQty = BigDecimal(10),
			tradeAmount = BigDecimal(10),
		)

		writeSecService.saveCashTokenTrading(entity)
	}

}
