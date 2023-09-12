package io.medium.poc.api.controller.otc

import io.medium.poc.api.controller.otc.request.query.OctAssetInfoQuery
import io.medium.poc.api.controller.otc.request.query.OctEnforcementResultQuery
import io.medium.poc.api.controller.otc.request.query.OctPriceCheckQuery
import io.medium.poc.api.usecase.otc.ReadOctAssetInfoUseCase
import io.medium.poc.api.usecase.otc.ReadOtcStoOrderBookUseCase
import io.medium.poc.api.usecase.otc.ReadOtcStoTradingUseCase
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.common.model.response.ResultResponsePagination
import io.medium.poc.domain.otc.model.dto.OctAssetDto
import io.medium.poc.domain.otc.model.dto.OctEnforcementResultDto
import io.medium.poc.domain.otc.model.dto.OctPriceCheckDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 장외거 read 컨트롤러
 */
@RestController
@RequestMapping("/api/poc/oct")
@Tag(name = "장외거래 조회용 API", description = "장외거래 조회 API 를 제공한다.")
class ReadOctController(
    private val readOtcStoOrderBookUseCase: ReadOtcStoOrderBookUseCase,
    private val readOtcStoTradingUseCase: ReadOtcStoTradingUseCase,
    private val readOctAssetInfoUseCase: ReadOctAssetInfoUseCase,
) {

    /** =================== 장외거래 정보 endpoint =============== */

    /**
     * 장외거래중개업자_토큰증권 호가조회_호가조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getPriceCheckList")
    @Operation(
        summary = "장외거래중개업자_토큰증권 호가조회 API",
        description = "장외거래중개업자_토큰증권 호가조회 정보를 가져온다. 검색 조건을 빈값으로 보내면 전체 리스트를 가져온다."
    )
    fun getPriceCheckList(@RequestBody @Valid query: OctPriceCheckQuery): ResultResponsePagination<OctPriceCheckDto> {
        return readOtcStoOrderBookUseCase.getPriceCheckList(query)
    }

    /**
     * 장외거래중개업자_토큰증권 체결내역 체결결과조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getEnforcementResulList")
    @Operation(
            summary = "장외거래중개업자_토큰증권 체결내역 체결결과조회 API",
            description = "장외거래중개업자_토큰증권 체결내역 체결결과조회 정보를 가져온다. 검색 조건을 빈값으로 보내면 전체 리스트를 가져온다."
    )
    fun getEnforcementResulList(
            @RequestBody @Valid query: OctEnforcementResultQuery
    ): ResultResponsePagination<OctEnforcementResultDto> {
        return readOtcStoTradingUseCase.getEnforcementResulList(query)
    }


    /**
     * 장외거래중개업자_고객계좌잔고_보유자산정보조회
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getAssetInfo")
    @Operation(
            summary = "장외거래중개업자_고객계좌잔고_보유자산정보조회 API",
            description = "장외거래중개업자_고객계좌잔고_보유자산정보조회 정보를 가져온다."
    )
    fun getAssetInfo(
            @RequestBody @Valid query: OctAssetInfoQuery
    ): ResultResponse<OctAssetDto> {
        return readOctAssetInfoUseCase.execute(query)
    }
}
