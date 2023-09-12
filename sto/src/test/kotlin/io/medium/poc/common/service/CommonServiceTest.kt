package io.medium.poc.common.service

import io.medium.poc.common.procedure.ProceduresCallService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

@SpringBootTest
class CommonServiceTest @Autowired constructor(
	private val proceduresCallService: ProceduresCallService,
) {

	@Test
	@DisplayName("callProcOTCStoOrderBook 테스트")
	fun callProcOTCStoOrderBook_TEST() {
		val custMgmtInstNo = "test"
		val custMgmtInstAcntNo = "test1"
		val stoItemNo = "TEST"
		val orderType = "01"
		val orderQty = BigDecimal.ONE
		val orderPrice = BigDecimal.ONE
		val orderTotalAmt = BigDecimal.ONE
		val orderCancelYn = "Y"
		val pair = proceduresCallService.callProcOTCStoOrderBook(
				custMgmtInstNo,
				custMgmtInstAcntNo,
				stoItemNo,
				orderType,
				orderQty,
				orderPrice,
				orderTotalAmt,
				orderCancelYn,
		)
		println(pair.first)
		println(pair.second)
	}

}
