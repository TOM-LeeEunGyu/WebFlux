package io.medium.poc.common.external.request

import java.math.BigDecimal

data class TradeStoRequest(
    val fcn: String = "tradeSto",
    val args: TradeStoArgs,
)

data class TradeStoArgs(
    val fromAuthWalletId: String,
    val fromSecComId: String,
    val toAuthWalletId: String,
    val toSecComId: String,
    val stoId: String,
    val balanceAmount: BigDecimal,
    val remarks: String,
)