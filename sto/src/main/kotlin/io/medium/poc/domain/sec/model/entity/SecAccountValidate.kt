package io.medium.poc.domain.sec.model.entity

import io.medium.poc.common.code.AccountValidateYn
import io.medium.poc.common.code.YesOrNo
import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 증권사 계좌유효성확인내역
 */
@Entity
@Table(name = "SEC_ACNT_VALIDATE")
class SecAccountValidate(

    /** composite id */
    @EmbeddedId
    val compositeId: SecAccountValidateMultiKeys,

    /** 요청일자 */
    @Column(name = "REQ_DT", length = 50)
    val reqDt: LocalDate? = null,

    /** 청약자명 */
    @Column(name = "SBSCR_NAME", length = 50)
    val sbscrName: String? = null,

    /** 확인일시 */
    @Column(name = "VALIDATE_DTM")
    val validateDtm: LocalDateTime? = null,

    /** 계좌유효성확인여부 */
    @Column(name = "ACNT_VALIDATE_YN", length = 1)
    @Convert(converter = AccountValidateYn.CodeConverter::class)
    var acntValidateYn: AccountValidateYn? = null,

    /** 전송여부 */
    @Column(name = "SEND_YN", length = 1)
    val sendYn: YesOrNo? = YesOrNo.N,
)

@Embeddable
data class SecAccountValidateMultiKeys(
    /** 고객관리기관번호 */
    @Column(name = "CUST_MGMT_INST_NO", length = 5)
    val custMgmtInstNo: String,

    /** 고객관리기관계좌번호 */
    @Column(name = "CUST_MGMT_INST_ACNT_NO", length = 20)
    val custMgmtInstAcntNo: String,

    /** 확인순번 */
    @Column(name = "CONFIRM_NO", length = 5)
    val confirmNo: Long,
): Serializable