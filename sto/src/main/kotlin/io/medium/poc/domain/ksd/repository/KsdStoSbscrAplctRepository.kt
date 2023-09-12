package io.medium.poc.domain.ksd.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.ksd.model.entity.KsdStoSbscrAplct
import io.medium.poc.domain.ksd.model.entity.KsdStoSbscrAplctMultiKeys
import io.medium.poc.domain.ksd.repository.custom.CustomKsdStoSbscrAplctRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * 예탁원 STO 청약신청내역 repository
 */
interface KsdStoSbscrAplctRepository
    : BaseRepository<KsdStoSbscrAplct, KsdStoSbscrAplctMultiKeys>, CustomKsdStoSbscrAplctRepository
{
    @Query("SELECT kssa FROM KsdStoSbscrAplct kssa " +
           " WHERE kssa.compositeId.issueMgmtInstNo = :issueMgmtInstNo " +
           "   AND kssa.compositeId.issueMgmtInstAcntNo = :issueMgmtInstAcntNo" +
           "   AND kssa.compositeId.stoItemNo = :stoItemNo")
    fun findByIssueMgmtInstNoAndIssueMgmtInstAcntNoAndStoItemNo(
        @Param("issueMgmtInstNo") issueMgmtInstNo: String,
        @Param("issueMgmtInstAcntNo") issueMgmtInstAcntNo: String,
        @Param("stoItemNo") stoItemNo: String
    ): List<KsdStoSbscrAplct>

}
