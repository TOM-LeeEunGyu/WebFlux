package io.medium.poc.domain.com.model.entity

import io.medium.poc.common.code.InvestorIdType
import jakarta.persistence.*

@Entity
@Table(name = "COM_INVESTOR")
class ComInvestor(

    /** 투자자 ID */
    @Id
    @Column(name = "INVESTOR_ID", length = 10)
    val investorId: String,

    /** 투자자명 */
    @Column(name = "INVESTOR_NAME", length = 50)
    val investorName: String? = null,

    /** 고객관리기관번호 */
    @Column(name = "CUST_MGMT_INST_NO", length = 5)
    val custMgmtInstNo: String? = null,

    /** 고객관리기관계좌번호 */
    @Column(name = "CUST_MGMT_INST_ACNT_NO", length = 20)
    val custMgmtInstAcntNo: String? = null,

    /** 투자자식별정보구분 */
    @Column(name = "INVST_ID_TYPE", length = 2)
    @Convert(converter = InvestorIdType.CodeConverter::class)
    val investorIdType: InvestorIdType? = null,

    /** 투자자식별정보 */
    @Column(name = "INVST_ID", length = 13)
    val investId: String? = null,
)