package io.medium.poc.common.procedure

import io.medium.poc.common.code.IncinerationReasonType
import io.medium.poc.common.code.LocalDateForm
import io.medium.poc.common.code.StoRightType
import io.medium.poc.common.exception.CallProceduresException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcCall
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class ProceduresCallService(
    private val jdbc: JdbcTemplate,
) {

    /** =================== 은행 프로시져 call =============== */

    /**
     * 캐시토큰 거래 생성 및 캐시토큰 State 변경 처리
     * call procBNK_bnkCashTokenTrading
     * @Param: stoItemNo: STO 종목번호
     */
    fun callProcBNKBnkCashTokenTrading(
        bankInstNo: String,
        custMgmtInstNo: String,
        cashId: String,
        cashTokenTrdType: String,
        trdQty: BigDecimal,
        trdAmt: BigDecimal,
    ): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procBNK_bnkCashTokenTrading")
        val params = MapSqlParameterSource()
        params.addValue("I_bankInstNo", bankInstNo)
        params.addValue("I_custMgmtInstNo", custMgmtInstNo)
        params.addValue("I_cashId", cashId)
        params.addValue("I_cashTokenTrdType", cashTokenTrdType)
        params.addValue("I_trdQty", trdQty)
        params.addValue("I_trdAmt", trdAmt)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("캐시토큰거래 프로시져[procBNK_bnkCashTokenTrading]호출중 장애가 발생했습니다.")
        }
    }

    /**
     * 청약증거금납입확인 프로시져
     * call procBNK_isrStoSbscrAplct
     * @Param: depositDt: 입금일자
     */
    fun callProcBNKIsrStoSbscrAplct(depositDt: LocalDate): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procBNK_isrStoSbscrAplct")
        val params = MapSqlParameterSource()
        params.addValue("I_depositDt", LocalDateForm.YYYY_MM_DD_NO_DELIMITER.transform(depositDt))
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("청약증거금납입확인 프로시져[procBNK_isrStoSbscrAplct]호출중 장애가 발생했습니다.")
        }
    }

    /**
     * 발행대금정산처리
     * call procBNK_secCustAcnt
     * @Param: stoItemNo: STO 종목번호
     */
    fun callProcBNKSecCustAcnt(stoItemNo: String): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procBNK_secCustAcnt")
        val params = MapSqlParameterSource()
        params.addValue("I_stoItemNo", stoItemNo)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("발행대금정산처리 프로시져[procBNK_secCustAcnt]호출중 장애가 발생했습니다.")
        }
    }

    /** =================== 발행사 프로시져 call =============== */


    /**
     * 발행사 STO 권리 내역(소각) 생성 프로시져
     * call procISR_isrCreateStoRight
     * @param stoItemNo: STO 종목번호
     * @param rightBaseDt: 권리 내역 등록 날짜
     * @param stoRightType: STO 권리구분
     * @param stoRightRto: STO 권리비율
     * @param incinerationReasonType: 소각사유구분
     */
    fun callProcISRCreateStoRight(
        stoItemNo: String,
        rightBaseDt: LocalDate,
        stoRightType: StoRightType,
        stoRightRto: Int,
        incinerationReasonType: IncinerationReasonType,
    ): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procISR_isrCreateStoRight")
        val params = MapSqlParameterSource()
        params.addValue("I_STO_ITEM_NO", stoItemNo)
        params.addValue("I_RIGHT_BASE_DT", rightBaseDt)
        params.addValue("I_STO_RIGHT_TYPE", stoRightType.code)
        params.addValue("I_STO_RIGHT_RTO", stoRightRto)
        params.addValue("I_INCINERATION_REASON_TYPE", incinerationReasonType.code)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("발행사 STO 권리 내역(소각) 생성 프로시져[procISR_isrCreateStoRight]호출중 장애가 발생했습니다.")
        }
    }

    /**
     * 토큰증권 최초발행처리 프로시져
     * call procISR_isrCreateStoRight
     * @param issueMgmtInstNo: 발행관리기관번호
     * @param issueMgmtInstAcntNo: 발행관리기관계좌번호
     * @param stoItemNo: STO 종목번호
     */
    fun callProcISRStoIssue(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procISR_isrStoIssue")
        val params = MapSqlParameterSource()
        params.addValue("I_issueMgmtInstNo", issueMgmtInstNo)
        params.addValue("I_issueMgmtInstAcntNo", issueMgmtInstAcntNo)
        params.addValue("I_stoItemNo", stoItemNo)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("토큰증권 최초발행처리 프로시져[procISR_isrStoIssue]호출중 장애가 발생했습니다.")
        }
    }

    /**
     * 청약배정처리 프로시져 -> 청약기간이 종료된 이후에 청약자별 배정내역을 생성
     * call procISR_isrStoSbscrAssign
     * @param issueMgmtInstNo: 발행관리기관번호
     * @param issueMgmtInstAcntNo: 발행관리기관계좌번호
     * @param stoItemNo: STO 종목번호
     */
    fun callProcISRIsrStoSbscrAssign(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procISR_isrStoSbscrAssign")
        val params = MapSqlParameterSource()
        params.addValue("I_issueMgmtInstNo", issueMgmtInstNo)
        params.addValue("I_issueMgmtInstAcntNo", issueMgmtInstAcntNo)
        params.addValue("I_stoItemNo", stoItemNo)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("청약배정처리 프로시져[procISR_isrStoSbscrAssign]호출중 장애가 발생했습니다.")
        }
    }

    /**
     * 투자자청약승인요청 프로시져 -> 투자자가 신청한 청약내역을 예탁원에 전송
     * call procISR_ksdStoSbscrAplct
     * @param issueMgmtInstNo: 발행관리기관번호
     * @param issueMgmtInstAcntNo: 발행관리기관계좌번호
     * @param stoItemNo: STO 종목번호
     */
    fun callProcISRKsdStoSbscrAplct(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procISR_ksdStoSbscrAplct")
        val params = MapSqlParameterSource()
        params.addValue("I_ISSUE_MGMT_INST_NO", issueMgmtInstNo)
        params.addValue("I_ISSUE_MGMT_INST_ACNT_NO", issueMgmtInstAcntNo)
        params.addValue("I_STO_ITEM_NO", stoItemNo)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("투자자청약승인요청 프로시져[procISR_ksdStoSbscrAplct]호출중 장애가 발생했습니다.")
        }
    }

    /**
     * 위탁계좌 유효성확인요청 프로시져
     * call procISR_secAcntValidate
     * @param issueMgmtInstNo: 발행관리기관번호
     * @param issueMgmtInstAcntNo: 발행관리기관계좌번호
     * @param stoItemNo: STO 종목번호
     */
    fun callProcISRSecAcntValidate(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procISR_secAcntValidate")
        val params = MapSqlParameterSource()
        params.addValue("I_ISSUE_MGMT_INST_NO", issueMgmtInstNo)
        params.addValue("I_ISSUE_MGMT_INST_ACNT_NO", issueMgmtInstAcntNo)
        params.addValue("I_STO_ITEM_NO", stoItemNo)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("위탁계좌 유효성확인요청 프로시져[procISR_secAcntValidate]호출중 장애가 발생했습니다.")
        }
    }


    /** =================== 예탁원 프로시져 call =============== */

    /**
     * 공모신청승인 프로시져 -> 발행사에서 승인요청한 공모정보의 승인을 처리하고 발행사로 승인여부 전송
     * call procKSD_ksdStoIssueAplct
     * @param issueMgmtInstNo: 발행관리기관번호
     * @param issueMgmtInstAcntNo: 발행관리기관계좌번호
     * @param issueNo: 이슈발행번호
     */
    fun callProcKSDStoIssueAplct(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        issueNo: String,
    ): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procKSD_ksdStoIssueAplct")
        val params = MapSqlParameterSource()
        params.addValue("I_issueMgmtInstNo", issueMgmtInstNo)
        params.addValue("I_issueMgmtInstAcntNo", issueMgmtInstAcntNo)
        params.addValue("I_issueNo", issueNo)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("공모신청승인 프로시져[procKSD_ksdStoIssueAplct]호출중 장애가 발생했습니다.")
        }
    }

    /**
     * 투자자청약승인 프로시져
     * call procKSD_ksdStoSbscrAplct
     */
    fun callProcKSDKsdStoSbscrAplct(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procKSD_ksdStoSbscrAplct")
        val params = MapSqlParameterSource()
        params.addValue("I_ISSUE_MGMT_INST_NO", issueMgmtInstNo)
        params.addValue("I_ISSUE_MGMT_INST_ACNT_NO", issueMgmtInstAcntNo)
        params.addValue("I_STO_ITEM_NO", stoItemNo)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("투자자청약승인 프로시져[procKSD_ksdStoSbscrAplct]호출중 장애가 발생했습니다.")
        }
    }

    /** =================== 장외거래 프로시져 call =============== */

    /**
     * 토큰증권 호가등록 프로시져
     * call procOTC_otcStoOrderBook
     * @param custMgmtInstNo: 고객관리기관번호
     * @param custMgmtInstAcntNo: 고객관리기관계좌번호
     * @param stoItemNo: STO 종목번호
     * @param orderType: 주문구분
     * @param orderQty: 주문수량
     * @param orderPrice: 주문가격
     * @param orderTotalAmt: 주문총액
     * @param orderCancelYn: 주문취소여부
     *
     * @return O_orderCkYn
     */
    fun callProcOTCStoOrderBook(
        custMgmtInstNo: String,
        custMgmtInstAcntNo: String,
        stoItemNo: String,
        orderType: String,
        orderQty: BigDecimal,
        orderPrice: BigDecimal,
        orderTotalAmt: BigDecimal,
        orderCancelYn: String,
    ): Pair<Boolean, String> {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procOTC_otcStoOrderBook")
        val params = MapSqlParameterSource()
        params.addValue("I_custMgmtInstNo", custMgmtInstNo)
        params.addValue("I_custMgmtInstAcntNo", custMgmtInstAcntNo)
        params.addValue("I_stoItemNo", stoItemNo)
        params.addValue("I_orderType", orderType)
        params.addValue("I_orderQty", orderQty)
        params.addValue("I_orderPrice", orderPrice)
        params.addValue("I_orderTotalAmt", orderTotalAmt)
        params.addValue("I_orderCancelYn", orderCancelYn)
        return try {
            val call = jdbcCall.execute(params)
            val orderCkYn = call["O_orderCkYn"] as ByteArray
            val msg = call["O_msg"].toString()
            val isOrderCkYn = String(orderCkYn) == "Y"
            isOrderCkYn to msg
        } catch (e: Exception) {
            throw CallProceduresException("토큰증권 호가등록 프로시져[procOTC_otcStoOrderBook]호출중 장애가 발생했습니다.")
        }
    }

    /**
     * 토큰증권 체결처리 프로시져
     * 토큰증권의 체결에 대한 매매증거수량이나 매매증거금을 해지하고 예수금이나 잔고에 반영
     * call procOTC_otcStoTrading
     * @param selCustMgmtInstNo: 매도고객관리기관번호
     * @param selCustMgmtInstAcntNo: 매도고객관리기관계좌번호
     * @param buyCustMgmtInstNo: 매수고객관리기관번호
     * @param buyCustMgmtInstAcntNo: 매수고객관리기관계좌번호
     * @param stoItemNo: STO 종목번호
     * @param trdType: 거래타입
     * @param trdQty: 거래량
     * @param trdAmt: 거래금액
     * @param orderNo: 주문순번
     */
    fun callProcOTCStoTrading(
        selCustMgmtInstNo: String,
        selCustMgmtInstAcntNo: String,
        buyCustMgmtInstNo: String,
        buyCustMgmtInstAcntNo: String,
        stoItemNo: String,
        trdType: String,
        trdQty: BigDecimal,
        trdAmt: BigDecimal,
        orderNo: Long,
    ): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procOTC_otcStoTrading")
        val params = MapSqlParameterSource()
        params.addValue("I_selCustMgmtInstNo", selCustMgmtInstNo)
        params.addValue("I_selCustMgmtInstAcntNo", selCustMgmtInstAcntNo)
        params.addValue("I_buyCustMgmtInstNo", buyCustMgmtInstNo)
        params.addValue("I_buyCustMgmtInstAcntNo", buyCustMgmtInstAcntNo)
        params.addValue("I_stoItemNo", stoItemNo)
        params.addValue("I_trdType", trdType)
        params.addValue("I_trdQty", trdQty)
        params.addValue("I_trdAmt", trdAmt)
        params.addValue("I_orderNo", orderNo)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("토큰증권 체결처리 프로시져[procOTC_otcStoTrading]호출중 장애가 발생했습니다.")
        }
    }

    /**
     * 토큰증권 STATE 처리 프로시져
     * call procOTC_otcStoTrading_STATE
     * @param selCustMgmtInstNo: 매도고객관리기관번호
     * @param selCustMgmtInstAcntNo: 매도고객관리기관계좌번호
     * @param buyCustMgmtInstNo: 매수고객관리기관번호
     * @param buyCustMgmtInstAcntNo: 매수고객관리기관계좌번호
     * @param stoItemNo: STO 종목번호
     * @param trdType: 거래타입
     * @param trdQty: 거래량
     * @param trdAmt: 거래금액
     */
    fun callProcOTCStoTradingState(
        selCustMgmtInstNo: String,
        selCustMgmtInstAcntNo: String,
        buyCustMgmtInstNo: String,
        buyCustMgmtInstAcntNo: String,
        stoItemNo: String,
        trdType: String,
        trdQty: BigDecimal,
        trdAmt: BigDecimal,
    ): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procOTC_otcStoTrading_STATE")
        val params = MapSqlParameterSource()
        params.addValue("I_selCustMgmtInstNo", selCustMgmtInstNo)
        params.addValue("I_selCustMgmtInstAcntNo", selCustMgmtInstAcntNo)
        params.addValue("I_buyCustMgmtInstNo", buyCustMgmtInstNo)
        params.addValue("I_buyCustMgmtInstAcntNo", buyCustMgmtInstAcntNo)
        params.addValue("I_stoItemNo", stoItemNo)
        params.addValue("I_trdType", trdType)
        params.addValue("I_trdQty", trdQty)
        params.addValue("I_trdAmt", trdAmt)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("토큰증권 STATE 처리 프로시져[procOTC_otcStoTrading_STATE]호출중 장애가 발생했습니다.")
        }
    }

    /** =================== 증권사 프로시져 call =============== */

    /**
     * 위탁계좌 유효성확인결과 프로시져
     * call procSEC_isrStoSbscrAplct
     * @param: validateDt: 계좌유효성확인요청일자
     */
    fun callProcSECIsrStoSbscrAplct(
        custMgmtInstNo: String,
        custMgmtInstAcntNo: String,
        confirmNo: Long,
        reqDt: LocalDate,
        acntValidateYn: String,
    ): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procSEC_isrStoSbscrAplct")
        val params = MapSqlParameterSource()
        params.addValue("I_CUST_MGMT_INST_NO", custMgmtInstNo)
        params.addValue("I_CUST_MGMT_INST_ACNT_NO", custMgmtInstAcntNo)
        params.addValue("I_CONFIRM_NO", confirmNo)
        params.addValue("I_REQ_DT", LocalDateForm.YYYY_MM_DD_NO_DELIMITER.transform(reqDt))
        params.addValue("I_ACNT_VALIDATE_YN", acntValidateYn)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("위탁계좌 유효성확인결과 프로시져[procSEC_isrStoSbscrAplct]호출중 장애가 발생했습니다.")
        }
    }

    /**
     * 토큰증권입고 프로시져
     * call procSEC_secCustAcntBalance
     * @param: stoItemNo: STO 종목번호
     */
    fun callProcSECSecCustAcntBalance(stoItemNo: String): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procSEC_secCustAcntBalance")
        val params = MapSqlParameterSource()
        params.addValue("I_STO_ITEM_NO", stoItemNo)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("토큰증권입고 프로시져[procSEC_secCustAcntBalance]호출중 장애가 발생했습니다.")
        }
    }

    /**
     * 토큰증권소각 프로시져
     * call procSEC_secCustAcntBalance_INCNR
     * @param: stoItemNo: STO 종목번호
     * @param: custMgmtInstNo: 고객관리기관번호
     */
    fun callProcSECSecCustAcntBalanceINCNR(stoItemNo: String, custMgmtInstNo: String): Any {
        val jdbcCall = SimpleJdbcCall(jdbc).withProcedureName("procSEC_secCustAcntBalance_INCNR")
        val params = MapSqlParameterSource()
        params.addValue("I_STO_ITEM_NO", stoItemNo)
        params.addValue("I_CUST_MGMT_INST_NO", custMgmtInstNo)
        return try {
            jdbcCall.execute(params)
        } catch (e: Exception) {
            throw CallProceduresException("토큰증권소각 프로시져[procSEC_secCustAcntBalance_INCNR]호출중 장애가 발생했습니다.")
        }
    }

}
