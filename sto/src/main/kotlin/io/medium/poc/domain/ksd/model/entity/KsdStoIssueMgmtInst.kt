package io.medium.poc.domain.ksd.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

/**
 * 예탁원 STO 발행관리기관정보
 */
@Entity
@Table(name = "KSD_STO_ISSUE_MGMT_INST")
class KsdStoIssueMgmtInst(

    /** 발행관리기관번호 */
    @Id
    @Column(name = "ISSUE_MGMT_INST_NO", length = 5)
    val issueMgmtInstNo: String,

    /** 발행관리기관명 */
    @Column(name = "ISSUE_MGMT_INST_NAME", length = 100)
    val issueMgmtInstName: String? = null,

    /** 발행관리기관대표자 */
    @Column(name = "ISSUE_MGMT_INST_RPRSN_NAME", length = 100)
    val issueMgmtInstRprsnName: String? = null,

    /** 발행관리기관담당자 */
    @Column(name = "ISSUE_MGMT_INST_CHARGE_NAME", length = 100)
    val issueMgmtInstChargeName: String? = null,

    /** 발행관리기관연락처 */
    @Column(name = "ISSUE_MGMT_INST_CONTACTPNT", length = 50)
    val issueMgmtInstContactpnt: String? = null,

    /** 발행관리기관이메일주소 */
    @Column(name = "ISSUE_MGMT_INST_EMAIL", length = 100)
    val issueMgmtInstEmail: String? = null,

    /** 발행관리기관주소 */
    @Column(name = "ISSUE_MGMT_INST_ADDR", length = 300)
    val issueMgmtInstAddr: String? = null,

    /** 발행관리기관사업자등록번호 */
    @Column(name = "ISSUE_MGMT_INST_BUSI_REG_NO", length = 10)
    val issueMgmtInstBusiRegNo: String? = null,

    /** 발행관리기관법인등록번호 */
    @Column(name = "ISSUE_MGMT_INST_CORP_REG_NO", length = 13)
    val issueMgmtInstCorpRegNo: String? = null,
)