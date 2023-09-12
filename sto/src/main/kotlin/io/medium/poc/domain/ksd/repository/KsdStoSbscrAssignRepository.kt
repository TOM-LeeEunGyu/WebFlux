package io.medium.poc.domain.ksd.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.ksd.model.entity.*
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * 예탁원 STO 청약배정내역 repository
 */
interface KsdStoSbscrAssignRepository: BaseRepository<KsdStoSbscrAssign, KsdStoSbscrAssignMultiKeys> {

    @Query("SELECT kssa FROM KsdStoSbscrAssign kssa " +
           " WHERE kssa.compositeId.issueMgmtInstNo = :issueMgmtInstNo " +
           "   AND kssa.compositeId.issueMgmtInstAcntNo = :issueMgmtInstAcntNo" +
           "   AND kssa.compositeId.stoItemNo = :stoItemNo")
    fun findByIssueMgmtInstNoAndIssueMgmtInstAcntNoAndStoItemNo(
        @Param("issueMgmtInstNo") issueMgmtInstNo: String,
        @Param("issueMgmtInstAcntNo") issueMgmtInstAcntNo: String,
        @Param("stoItemNo") stoItemNo: String
    ): List<KsdStoSbscrAssign>
}
