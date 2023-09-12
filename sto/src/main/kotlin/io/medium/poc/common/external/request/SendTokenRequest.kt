package io.medium.poc.common.external.request

import java.math.BigDecimal

data class SendTokenRequest(
    val fcn: String = "sendToken",
    val args: SendTokenArgs,
)

data class SendTokenArgs(
    val fromAuthWalletId: String,
    val toAuthWalletId: String,
    val tokenId: String,
    val balanceAmount: BigDecimal,
    val remarks: String,
)