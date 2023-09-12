package io.medium.poc.domain.com.model.entity

import io.medium.poc.common.code.IncinerationYn
import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.common.code.TradeStartYn
import jakarta.persistence.*

@Entity
@Table(name = "COM_STO_ITEM")
class ComStoItem(

    /** STO 종목번호 */
    @Id
    @Column(name = "STO_ITEM_NO", length = 12)
    val stoItemNo: String,

    /** STO 종목명 */
    @Column(name = "STO_ITEM_NAME", length = 100)
    val stoItemName: String? = null,

    /** STO 기초자산구분 */
    @Column(name = "STO_BASE_ASSET_TYPE", length = 2)
    @Convert(converter = StoBaseAssetType.CodeConverter::class)
    val stoBaseAssetType: StoBaseAssetType? = null,

    /** 매매가능여부 */
    @Column(name = "TRD_START_YN", length = 1)
    @Convert(converter = TradeStartYn.CodeConverter::class)
    var tradeStartYn: TradeStartYn? = null,

    /** 소각여부 */
    @Column(name = "INCINERATION_YN", length = 1)
    @Convert(converter = IncinerationYn.CodeConverter::class)
    var incinerationYn: IncinerationYn? = null,

    /** 발행관리기관번호 */
    @Column(name = "ISSUE_MGMT_INST_NO", length = 5)
    val issueMgmtInstNo: String? = null,

    /** 발행관리기관계좌번호 */
    @Column(name = "ISSUE_MGMT_INST_ACNT_NO", length = 20)
    val issueMgmtInstAcntNo: String? = null,
)