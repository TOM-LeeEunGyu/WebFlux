package io.medium.poc.domain.ksd.model.entity

import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.common.code.YesOrNo
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

/**
 * 예탁원 STO 청약신청내역
 */
@Entity
@DynamicUpdate
@Table(name = "KSD_STO_ISSUE_APLCT")
class KsdStoIssueAplct(

    /** composite id */
    @EmbeddedId
    val compositeId: KsdStoIssueAplctMultiKeys,

    /** 발행신청일자 */
    @Column(name = "ISSUE_APLCT_DT")
    val issueAplctDt: LocalDate? = null,

    /** 발행승인일자 */
    @Column(name = "ISSUE_APPR_DT")
    var issueApprDt: LocalDate? = null,

    /** 발행신청수량 */
    @Column(name = "ISSUE_APPR_QTY")
    val issueApprQty: BigDecimal? = BigDecimal.ZERO,

    /** STO 종목번호 */
    @Column(name = "STO_ITEM_NO", length = 12)
    var stoItemNo: String? = null,

    /** STO 종목명 */
    @Column(name = "STO_ITEM_NAME", length = 100)
    val stoItemName: String? = null,

    /** 발행상품평가금액 */
    @Column(name = "ISSUE_GOODS_EVL_AMT")
    val issueGoodsEvlAmt: BigDecimal? = BigDecimal.ZERO,

    /** 발행가 */
    @Column(name = "ISSUE_PRICE")
    val issuePrice: BigDecimal? = BigDecimal.ZERO,

    /** STO 기초자산구분 */
    @Column(name = "STO_BASE_ASSET_TYPE", length = 2)
    @Convert(converter = StoBaseAssetType.CodeConverter::class)
    val stoBaseAssetType: StoBaseAssetType? = null,

    /** 발행상품평가자료 */
    @Column(name = "ISSUE_GOODS_EVL_DATA", length = 300)
    val issueGoodsEvlData: String? = null,

    /** 발행상품소유증빙자료 */
    @Column(name = "ISSUE_GOODS_OWN_EVIDENCE_DATA", length = 300)
    val issueGoodsOwnEvidenceData: String? = null,

    /** 승인여부 */
    @Column(name = "APPR_YN", length = 1)
    var apprYn: YesOrNo? = YesOrNo.N,

    /** 송신여부 */
    @Column(name = "SND_YN", length = 1)
    var sndYn: YesOrNo? = YesOrNo.N,
)

@Embeddable
data class KsdStoIssueAplctMultiKeys(
    /** 발행관리기관번호 */
    @Column(name = "ISSUE_MGMT_INST_NO", length = 5)
    val issueMgmtInstNo: String,

    /** 발행관리기관계좌번호 */
    @Column(name = "ISSUE_MGMT_INST_ACNT_NO", length = 20)
    val issueMgmtInstAcntNo: String,

    /** 발행인번호 */
    @Column(name = "ISSUE_NO", length = 10)
    val issueNo: String,
): Serializable