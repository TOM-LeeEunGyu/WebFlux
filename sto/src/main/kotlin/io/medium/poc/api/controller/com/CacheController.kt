package io.medium.poc.api.controller.com

import io.medium.poc.api.controller.com.model.CodeDto
import io.medium.poc.api.controller.com.model.CodeInfoDto
import io.medium.poc.api.controller.com.model.request.ComStoItemQuery
import io.medium.poc.api.usecase.com.ReadComStoItemUseCase
import io.medium.poc.common.code.*
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.domain.com.model.dto.ComStoItemDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/poc")
@RestController
@Tag(name = "공통 코드 정보 조회용 API", description = "공통 코드 정보를 조회한다.")
class CacheController(
    private val readComStoItemUseCase: ReadComStoItemUseCase,
) {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/common/codes")
    @Operation(
        summary = "코드 목록 리스트 API",
        description = "코드 목록 리스트를 조회한다."
    )
    fun codeList(): CodeDto {
        val yesOrNoValues = listOf(
            CodeInfoDto(YesOrNo.Y.name, "예"),
            CodeInfoDto(YesOrNo.N.name, "아니오")
        )

        return CodeDto(
            sendYn = yesOrNoValues,
            receiveYn = yesOrNoValues,
            approveYn = yesOrNoValues,
            accountValidateYn = AccountValidateYn.values().map { CodeInfoDto(it.code, it.description ) },
            subscriptionMarginAmountPayYn = SbscrMarginAmtPayYn.values().map { CodeInfoDto(it.code, it.description ) },
            orderCheckYn = OrderCheckYn.values().map { CodeInfoDto(it.code, it.description ) },
            tradingYn = TradingYn.values().map { CodeInfoDto(it.code, it.description ) },
            issueStatusType = IssueStatusType.values().map { CodeInfoDto(it.code, it.description ) },
            stoBaseAssetType = StoBaseAssetType.values().map { CodeInfoDto(it.code, it.description ) },
            investorIdType = InvestorIdType.values().map { CodeInfoDto(it.code, it.description ) },
            stoRightType = StoRightType.values().map { CodeInfoDto(it.code, it.description ) },
            tradeType = TradeType.values().map { CodeInfoDto(it.code, it.description ) },
            balanceType = BalanceType.values().map { CodeInfoDto(it.code, it.description ) },
            orderType = OrderType.values().map { CodeInfoDto(it.code, it.description ) },
            cashTokenTradeType = CashTokenTradeType.values().map { CodeInfoDto(it.code, it.description ) },
            incinerationReasonType = IncinerationReasonType.values().map { CodeInfoDto(it.code, it.description ) },
            incinerationYn = IncinerationYn.values().map { CodeInfoDto(it.code, it.description ) },
            tradeStartYn = TradeStartYn.values().map { CodeInfoDto(it.code, it.description ) },
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/common/stoItems")
    @Operation(
        summary = "공통 STO 종목 정보 목록조회 API",
        description = "공통 STO 종목 정보 목록을 조회한다."
    )
    fun comStoItems(@RequestBody @Valid query: ComStoItemQuery): ResultResponsePagination<ComStoItemDto> {
        return readComStoItemUseCase.execute(query)
    }
}
