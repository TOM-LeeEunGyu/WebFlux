package io.medium.poc.domain.ksd.repository

import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.ksd.model.entity.KsdStoIssueMgmt
import io.medium.poc.domain.ksd.model.entity.KsdStoIssueMgmtMultiKeys
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * 예탁원 STO 발행관리정보 repository
 */
interface KsdStoIssueMgmtRepository: BaseRepository<KsdStoIssueMgmt, KsdStoIssueMgmtMultiKeys> {
    @Query("SELECT ksim FROM KsdStoIssueMgmt ksim " +
           " WHERE ksim.compositeId.issueMgmtInstNo = :issueMgmtInstNo " +
           "   AND ksim.compositeId.issueMgmtInstAcntNo = :issueMgmtInstAcntNo" +
           "   AND ksim.compositeId.stoItemNo = :stoItemNo")
    fun findByIssueMgmtInstNoAndIssueMgmtInstAcntNoAndStoItemNo(
        @Param("issueMgmtInstNo") issueMgmtInstNo: String,
        @Param("issueMgmtInstAcntNo") issueMgmtInstAcntNo: String,
        @Param("stoItemNo") stoItemNo: String
    ): KsdStoIssueMgmt?

    fun findBySndYnAndIssueApprDtIsNotNull(sendYn: YesOrNo): List<KsdStoIssueMgmt>
}
