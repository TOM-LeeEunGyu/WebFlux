package io.medium.poc.domain.ksd.model.entity

import io.medium.poc.common.code.YesOrNo
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.math.BigDecimal
import java.time.LocalDate

/**
 * 예탁원 STO 발행인정보
 */
@Entity
@DynamicUpdate
@Table(name = "KSD_STO_ISSUE")
class KsdStoIssue(

    /** 발행인번호 | 증권 또는 디지털자산 소유주 */
    @Id
    @Column(name = "ISSUE_NO", length = 10)
    val issueNo: String,

    /** 발행인명 */
    @Column(name = "ISSUE_NAME", length = 100)
    val issueName: String? = null,

    /** 발행인명(단축) */
    @Column(name = "ISSUE_NAME_SRT", length = 50)
    val issueNameShort: String? = null,

    /** 발행인대표자 */
    @Column(name = "ISSUE_RPRSN_NAME", length = 100)
    val issueRprsnName: String? = null,

    /** 발행인담당자 */
    @Column(name = "ISSUE_CHARGE_NAME", length = 100)
    val issueChargeName: String? = null,

    /** 발행인연락처 */
    @Column(name = "ISSUE_CONTACTPNT", length = 50)
    val issueContactpnt: String? = null,

    /** 발행인담당자 */
    @Column(name = "ISSUE_EMAIL", length = 100)
    val issueEmail: String? = null,

    /** 발행인홈페이지 */
    @Column(name = "ISSUE_HOMEPAGE", length = 100)
    val issueHomepage: String? = null,

    /** 발행인주소 */
    @Column(name = "ISSUE_ADDR", length = 300)
    val issueAddr: String? = null,

    /** 발행인한도 */
    @Column(name = "ISSUE_LMT")
    val issueLmt: BigDecimal? = BigDecimal.ZERO,

    /** 발행인잔여한도 */
    @Column(name = "ISSUE_REMAIN_LMT")
    val issueRemainLmt: BigDecimal? = BigDecimal.ZERO,

    /** 발행인사업자등록번호 */
    @Column(name = "ISSUE_BUSI_REG_NO", length = 10)
    val issueBusiRegNo: String? = null,

    /** 발행인법인등록번호 */
    @Column(name = "ISSUE_CORP_REG_NO", length = 13)
    val issueCorpRegNo: String? = null,

    /** 최종발행일자 */
    @Column(name = "LST_ISSUE_DT")
    var lstIssueDt: LocalDate? = null,

    /** 발행인거래기관번호 */
    @Column(name = "ISSUE_TRD_INST_NO", length = 10)
    val issueTrdInstNo: String? = null,

    /** 발행인거래기관계좌번호 */
    @Column(name = "ISSUE_TRD_ACNT_NO", length = 20)
    val issueTrdAcntNo: String? = null,

    /** 승인여부 */
    @Column(name = "APPR_YN", length = 1)
    var apprYn: YesOrNo? = YesOrNo.N,

    /** 송신여부 */
    @Column(name = "SND_YN", length = 1)
    var sndYn: YesOrNo? = YesOrNo.N,
)