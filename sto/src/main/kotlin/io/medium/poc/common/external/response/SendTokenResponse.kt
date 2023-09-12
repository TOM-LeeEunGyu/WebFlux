package io.medium.poc.common.external.response

data class SendTokenResponse(
    val txId: String?,
    val code: Int?,
    val message: String?,
    val data: SendTokenData?,
) {
    fun isSuccess(): Boolean {
        return code == 200
    }
}

data class SendTokenData(
    val tradeId: String?,
)