package io.medium.poc.domain.bank.usecase

import io.medium.poc.api.controller.bank.request.command.BankCashTokenTradingCommand
import io.medium.poc.api.usecase.bank.WriteBankCashTokenTradingUseCase
import io.medium.poc.common.code.CashTokenTradeType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDateTime.now

@SpringBootTest
class WriteUseCaseTest @Autowired constructor(
    private val writeCashTokenTradingUseCase: WriteBankCashTokenTradingUseCase,
) {

	@Test
	@DisplayName("writeCashTokenTradingUseCase 테스트")
	fun writeCashTokenTradingUseCase_TEST() {
		// given
		val command = BankCashTokenTradingCommand(
			bankInstNo = "BINO1",
			custMgmtInstNo = "CMINO",
			cashId = "cashId",
			cashTokenTradeType = CashTokenTradeType.SELL.name,
			trdQty = BigDecimal(10),
			trdAmt = BigDecimal(10),
			trdDtm = now()
		)

		// then
		writeCashTokenTradingUseCase.execute(command)
	}

}
