package io.medium.poc.common.service

import io.medium.poc.common.code.CashTokenTradeType
import io.medium.poc.common.mdl.MdlCallService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

@SpringBootTest
class MdlServiceTest @Autowired constructor(
	private val mdlCallService: MdlCallService,
) {

	@Test
	@DisplayName("tradeSto 테스트")
	fun tradeSto_TEST() {
		val selCustMgmtInstNo = "selMIN"
		val selCustMgmtInstAcntNo = "selACCOUNT"
		val buyCustMgmtInstNo = "buyMIN"
		val buyCustMgmtInstAcntNo = "buyACCOUNT"
		val stoItemNo = "TEST"
		val trdQty = BigDecimal.TEN
		mdlCallService.tradeSto(
			selCustMgmtInstNo,
			selCustMgmtInstAcntNo,
			buyCustMgmtInstNo,
			buyCustMgmtInstAcntNo,
			stoItemNo,
			trdQty
		)
	}

	@Test
	@DisplayName("sendToken 테스트")
	fun sendToken_TEST() {
		val fromAuthWalletId = "FAWI"
		val toAuthWalletId = "TAWI"
		val tokenId = "TOKENID"
		val balanceAmount = BigDecimal.TEN
		val remarks = CashTokenTradeType.SELL.code
		mdlCallService.sendToken(
			fromAuthWalletId,
			toAuthWalletId,
			tokenId,
			balanceAmount,
			remarks,
		)
	}

}
