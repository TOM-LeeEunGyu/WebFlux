package io.medium.poc.common.external.response

data class TradeStoResponse(
    val txId: String?,
    val code: Int?,
    val message: String?,
    val data: TradeStoData?,
) {
    fun isSuccess(): Boolean {
        return code == 200
    }
}

data class TradeStoData(
    val stoTradeId: String?,
)