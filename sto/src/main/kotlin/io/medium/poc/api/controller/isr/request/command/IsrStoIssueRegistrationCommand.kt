package io.medium.poc.api.controller.isr.request.command

import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.constraint.EnumCheck
import io.medium.poc.common.utils.nowLocalDate
import io.medium.poc.domain.isr.model.entity.IsrStoIssue
import io.medium.poc.domain.isr.model.entity.IsrStoIssueAplct
import io.medium.poc.domain.isr.model.entity.IsrStoIssueAplctMultiKeys
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

@Schema(description = "발행사 공모등록 요청 객체")
data class IsrStoIssueRegistrationCommand(

    @Schema(description = "발행관리기관번호")
    @field:NotNull(message = "발행관리기관번호 정보는 필수입니다.")
    @field:NotBlank(message = "발행관리기관번호 정보는 필수입니다.")
    @field:Size(max = 5, message = "발행관리기관번호의 길이가 5를 넘을 수 없습니다.")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호")
    @field:NotNull(message = "발행관리기관계좌번호 정보는 필수입니다.")
    @field:NotBlank(message = "발행관리기관계좌번호 정보는 필수입니다.")
    @field:Size(max = 20, message = "발행관리기관계좌번호의 길이가 20를 넘을 수 없습니다.")
    val issueMgmtInstAcntNo: String,

    @Schema(description = "발행인명")
    @field:NotNull(message = "발행인명 정보는 필수입니다.")
    @field:NotBlank(message = "발행인명 정보는 필수입니다.")
    val issueName: String,

    @Schema(description = "발행인대표자")
    val issueRprsnName: String? = null,

    @Schema(description = "발행인담당자")
    val issueChargeName: String? = null,

    @Schema(description = "발행인연락처")
    val issueContactpnt: String? = null,

    @Schema(description = "발행인이메일주소")
    val issueEmail: String? = null,

    @Schema(description = "발행인홈페이지")
    val issueHomepage: String? = null,

    @Schema(description = "발행인주소")
    val issueAddr: String? = null,

    @Schema(description = "발행인사업자등록번호")
    val issueBusiRegNo: String? = null,

    @Schema(description = "발행인법인등록번호")
    val issueCorpRegNo: String? = null,

    @Schema(description = "발행인거래기관번호(은행)")
    @field:NotNull(message = "발행인거래기관번호(은행) 정보는 필수입니다")
    @field:NotBlank(message = "발행인거래기관번호(은행) 정보는 필수입니다")
    @field:Size(max = 5, message = "발행인거래기관번호(은행)의 길이가 5를 넘을 수 없습니다.")
    val issueTrdInstNo: String,

    @Schema(description = "발행인거래기관계좌번호")
    @field:NotNull(message = "발행인거래기관계좌번호 정보는 필수입니다.")
    @field:NotBlank(message = "발행인거래기관계좌번호 정보는 필수입니다.")
    @field:Size(max = 20, message = "발행관리기관계좌번호의 길이가 20를 넘을 수 없습니다.")
    val issueTrdAcntNo: String,

    // IsrStoIssueAplct 객체 생성에 필요한 값
    @Schema(description = "발행상품 이름(STO 종목명)")
    @field:NotNull(message = "발행상품 이름(STO 종목명) 정보는 필수입니다.")
    @field:NotBlank(message = "발행상품 이름(STO 종목명) 정보는 필수입니다.")
    val stoItemName: String,

    @Schema(description = "토큰증권 발행량(발행신청수량)")
    @field:NotNull(message = "토큰증권 발행량(발행신청수량) 정보는 필수입니다.")
    @field:Min(1, message = "토큰증권 발행량(발행신청수량)는 0보다 커야 합니다.")
    val issueApprQty: BigDecimal,

    @Schema(description = "발행상품 가치평가금액")
    @field:NotNull(message = "발행상품 가치평가금액 정보는 필수입니다.")
    @field:Min(1, message = "발행상품 가치평가금액은 0보다 커야 합니다.")
    val issueGoodsEvlAmt: BigDecimal,

    @Schema(description = "발행가")
    @field:NotNull(message = "발행가 정보는 필수입니다.")
    @field:Min(1, message = "발행가는 0보다 커야 합니다.")
    val issuePrice: BigDecimal,

    @Schema(description = "토큰증권 종류" ,example = "[01, 02]")
    @field:EnumCheck(enumClazz = StoBaseAssetType::class, message = "StoBaseAssetType 필드는 01, 02 만 가능합니다.")
    val stoBaseAssetType: String,

    @Schema(description = "상품 가치 평가 자료")
    val issueGoodsEvlData: String? = null,

    @Schema(description = "상품소유 증빙 자료")
    val issueGoodsOwnEvidenceData: String? = null,

) {
    fun toIsrStoIssueEntity(issueNo: String): IsrStoIssue {
         return IsrStoIssue(
            issueNo = issueNo,
            issueName = issueName,
            issueRprsnName = issueRprsnName,
            issueChargeName = issueChargeName,
            issueContactpnt = issueContactpnt,
            issueEmail = issueEmail,
            issueAddr = issueAddr,
            issueBusiRegNo= issueBusiRegNo,
            issueCorpRegNo = issueCorpRegNo,
            issueHomepage = issueHomepage,
            issueTrdInstNo = issueTrdInstNo,
            issueTrdAcntNo = issueTrdAcntNo,
            sndYn = YesOrNo.Y,
        )
    }

    fun toIsrStoIssueAplctCompositeId(issueNo: String): IsrStoIssueAplctMultiKeys {
        return IsrStoIssueAplctMultiKeys(
            issueMgmtInstNo = issueMgmtInstNo,
            issueMgmtInstAcntNo = issueMgmtInstAcntNo,
            issueNo = issueNo,
        )
    }

    fun toIsrStoIssueAplctEntity(issueNo: String): IsrStoIssueAplct {
        val compositeId = IsrStoIssueAplctMultiKeys(
            issueMgmtInstNo = issueMgmtInstNo,
            issueMgmtInstAcntNo = issueMgmtInstAcntNo,
            issueNo = issueNo,
        )
        return IsrStoIssueAplct(
            compositeId = compositeId,
            issueApprQty = issueApprQty,
            stoItemName = stoItemName,
            issueAplctDt = nowLocalDate(),
            stoBaseAssetType = StoBaseAssetType.fromCode(stoBaseAssetType),
            issueGoodsEvlAmt = issueGoodsEvlAmt,
            issuePrice = issuePrice,
            issueGoodsEvlData = issueGoodsEvlData,
            issueGoodsOwnEvidenceData= issueGoodsOwnEvidenceData,
            sndYn = YesOrNo.Y,
        )
    }

}
