package io.medium.poc.api.controller.procedure

import io.medium.poc.api.controller.procedure.request.command.*
import io.medium.poc.api.controller.procedure.response.OtcStoOrderBookCallResponse
import io.medium.poc.api.controller.sec.request.command.SecAccountValidateInfo
import io.medium.poc.common.code.CommonMessage
import io.medium.poc.common.exception.CallProceduresException
import io.medium.poc.common.mdl.MdlCallService
import io.medium.poc.common.model.response.CommonResponse
import io.medium.poc.common.procedure.ProceduresCallService
import io.medium.poc.common.utils.nowLocalDateTime
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/poc/procedures")
@Tag(name = "프로시져 호출 API", description = "프로시져 호출 API 를 제공한다.")
class CallProcedureController(
    private val proceduresCallService: ProceduresCallService,
    private val mdlCallService: MdlCallService,
) {

    /** =================== 은행 프로시져 endpoint =============== */

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procBNK_bnkCashTokenTrading")
    @Operation(
        summary = "캐시토큰 거래 생성 및 캐시토큰 State 변경 처리 프로시져",
        description = "캐시토큰 거래 생성 및 캐시토큰 State 변경 처리 프로시져"
    )
    fun callProcBNKBnkCashTokenTrading(@RequestBody @Valid command: BnkCashTokenTradingCall): CommonResponse {
        command.run {
            proceduresCallService.callProcBNKBnkCashTokenTrading(
                bankInstNo, custMgmtInstNo, cashId, cashTokenTrdType, trdQty, trdAmt
            )
            val pair = if(
                cashTokenTrdType().code == "01" ||
                cashTokenTrdType().code == "03"
            ) {
                bankInstNo to custMgmtInstNo
            } else {
                custMgmtInstNo to bankInstNo
            }
            mdlCallService.sendToken(
                fromAuthWalletId = pair.first,
                toAuthWalletId = pair.second,
                tokenId = cashId,
                trdQty = trdQty,
                remarks = cashTokenTrdType().description,
            )
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procBNK_isrStoSbscrAplct")
    @Operation(
        summary = "청약증거금납입확인 프로시져",
        description = "청약증거금납입확인 프로시져"
    )
    fun callProcBNKIsrStoSbscrAplct(@RequestBody @Valid command: BnkIsrStoAbscrAplctCall): CommonResponse {
        command.run {
            proceduresCallService.callProcBNKIsrStoSbscrAplct(depositDt)
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procBNK_secCustAcnt")
    @Operation(
        summary = "발행대금정산처리 프로시져",
        description = "발행대금정산처리 프로시져"
    )
    fun callProcBNKSecCustAcnt(@RequestBody @Valid command: BnkSecCustAcntCall): CommonResponse {
        command.run {
            proceduresCallService.callProcBNKSecCustAcnt(stoItemNo)
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /** =================== 발행사 프로시져 endpoint =============== */

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procISR_isrCreateStoRight")
    @Operation(
        summary = "발행사 STO 권리 내역(소각) 생성 프로시져",
        description = "발행사 STO 권리 내역(소각) 생성 프로시져"
    )
    fun callProcISRCreateStoRight(@RequestBody @Valid command: IsrStoRightCall): CommonResponse {
        command.run {
            proceduresCallService.callProcISRCreateStoRight(
                stoItemNo, rightBaseDt, stoRightType(), stoRightRto, incinerationReasonType()
            )
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procISR_isrStoIssue")
    @Operation(
        summary = "토큰증권 최초발행처리 프로시져",
        description = "토큰증권 최초발행처리 프로시져"
    )
    fun callProcISRStoIssue(@RequestBody @Valid command: IsrStoIssueCall): CommonResponse {
        command.run {
            proceduresCallService.callProcISRStoIssue(
                issueMgmtInstNo,
                issueMgmtInstAcntNo,
                stoItemNo,
            )
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procISR_isrStoSbscrAssign")
    @Operation(
        summary = "청약배정처리 프로시져",
        description = "청약배정처리 프로시져"
    )
    fun callProcISRStoSbscrAssign(@RequestBody @Valid command: IsrStoSbscrAssignCall): CommonResponse {
        command.run {
            proceduresCallService.callProcISRIsrStoSbscrAssign(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procISR_ksdStoSbscrAplct")
    @Operation(
        summary = "투자자청약승인요청 프로시져",
        description = "투자자청약승인요청 프로시져"
    )
    fun callProcISRKsdStoSbscrAplct(@RequestBody @Valid command: IsrKsdStoSbscrAplctCall): CommonResponse {
        command.run {
            proceduresCallService.callProcISRKsdStoSbscrAplct(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procISR_secAcntValidate")
    @Operation(
        summary = "위탁계좌 유효성확인요청 프로시져",
        description = "위탁계좌 유효성확인요청 프로시져"
    )
    fun callProcISRSecAcntValidate(@RequestBody @Valid command: IsrSecAcntValidateCall): CommonResponse {
        command.run {
            proceduresCallService.callProcISRSecAcntValidate(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /** =================== 예탁원 프로시져 endpoint =============== */

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procKSD_ksdStoIssueAplct")
    @Operation(
        summary = "공모신청승인 프로시져",
        description = "공모신청승인 프로시져: 발행사에서 승인요청한 공모정보의 승인을 처리하고 발행사로 승인여부 전송"
    )
    fun callProcKSDStoIssueAplct(@RequestBody @Valid command: KstStoIssueAplctCall): CommonResponse {
        command.run {
            proceduresCallService.callProcKSDStoIssueAplct(issueMgmtInstNo, issueMgmtInstAcntNo, issueNo)
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procKSD_ksdStoSbscrAplct")
    @Operation(
        summary = "투자자청약승인 프로시져",
        description = "투자자청약승인 프로시져"
    )
    fun callProcKSDStoSbscrAplct(@RequestBody @Valid command: KstStoSbscrAplctCall): CommonResponse {
        command.run {
            proceduresCallService.callProcKSDKsdStoSbscrAplct(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /** =================== 장외거래 프로시져 endpoint =============== */

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procOTC_otcStoOrderBook")
    @Operation(
        summary = "토큰증권 호가등록 프로시져",
        description = "토큰증권 호가등록 프로시져: 토큰증권의 주문에 대하여 증권사에서 매매증거수량이나 매매증거금을 설정"
    )
    fun callPocOTCStoOrderBook(@RequestBody @Valid command: OtcStoOrderBookCall): OtcStoOrderBookCallResponse {
        val pair = with(command) {
            proceduresCallService.callProcOTCStoOrderBook(
                custMgmtInstNo,
                custMgmtInstAcntNo,
                stoItemNo,
                orderType,
                orderQty,
                orderPrice,
                orderTotalAmt,
                orderCancelYn,
            )
        }
        if(pair.first) {
            return OtcStoOrderBookCallResponse(
                code = HttpStatus.OK.value(),
                message = CommonMessage.SUCCESS.code,
                orderCkYn = true,
                timestamp = nowLocalDateTime()
            )
        } else {
            throw CallProceduresException(pair.second)
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procOTC_otcStoTrading")
    @Operation(
        summary = "토큰증권 체결처리 프로시져",
        description = "토큰증권의 체결에 대한 매매증거수량이나 매매증거금을 해지하고 예수금이나 잔고에 반영정"
    )
    fun callProcOTCStoTrading(@RequestBody @Valid command: OtcStoTradingCall): CommonResponse {
        command.run {
            proceduresCallService.callProcOTCStoTrading(
                selCustMgmtInstNo,
                selCustMgmtInstAcntNo,
                buyCustMgmtInstNo,
                buyCustMgmtInstAcntNo,
                stoItemNo,
                trdType,
                trdQty,
                trdAmt,
                orderNo,
            )
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procOTC_otcStoTrading_STATE")
    @Operation(
        summary = "토큰증권 STATE 처리 프로시져",
        description = "토큰증권 STATE 처리 프로시져 호출"
    )
    fun callProcOTCStoTradingState(@RequestBody @Valid command: OtcStoTradingStateCall): CommonResponse {
        command.run {
            proceduresCallService.callProcOTCStoTradingState(
                selCustMgmtInstNo,
                selCustMgmtInstAcntNo,
                buyCustMgmtInstNo,
                buyCustMgmtInstAcntNo,
                stoItemNo,
                trdType,
                trdQty,
                trdAmt,
            )
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    /** =================== 증권사 프로시져 endpoint =============== */


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procSEC_isrStoSbscrAplct")
    @Operation(
        summary = "위탁계좌 유효성확인결과 프로시져",
        description = "위탁계좌 유효성확인결과 프로시져"
    )
    fun callProcSECIsrStoSbscrAplct(@RequestBody @Valid command: SecAccountValidateInfo): CommonResponse {
        command.run {
            proceduresCallService.callProcSECIsrStoSbscrAplct(
                custMgmtInstNo, custMgmtInstAcntNo, confirmNo, reqDt, acntValidateYn
            )
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procSEC_secCustAcntBalance")
    @Operation(
        summary = "토큰증권입고 프로시져",
        description = "토큰증권입고 프로시져: 토큰증권의 발행에서 투자자에게 배정된 잔고에 대하여 고객계좌에 잔고를 생성"
    )
    fun callProcSECSecCustAcntBalance(@RequestBody @Valid command: SecCustAcntBalanceCall): CommonResponse {
        command.run {
            proceduresCallService.callProcSECSecCustAcntBalance(stoItemNo)
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/procSEC_secCustAcntBalance_INCNR")
    @Operation(
        summary = "토큰증권소각 프로시져",
        description = "토큰증권소각 프로시져: 토큰증권의 소각에서 투자자가 보유한 잔고를 소각비율에 따라서 소각 처리"
    )
    fun callProcSECSecCustAcntBalanceINCNR(@RequestBody @Valid command: SecCustAcntBalanceIncnrCall): CommonResponse {
        command.run {
            proceduresCallService.callProcSECSecCustAcntBalanceINCNR(stoItemNo, custMgmtInstNo)
        }
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = nowLocalDateTime()
        )
    }

}
