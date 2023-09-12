package io.medium.poc.domain.sec.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.sec.model.entity.SecAccountValidate
import io.medium.poc.domain.sec.model.entity.SecAccountValidateMultiKeys
import org.springframework.data.jpa.repository.Query

interface SecAccountValidateRepository
    : BaseRepository<SecAccountValidate, SecAccountValidateMultiKeys> {
    @Query("SELECT s FROM SecAccountValidate s WHERE s.compositeId.custMgmtInstNo = :custMgmtInstNo AND s.compositeId.custMgmtInstAcntNo = :custMgmtInstAcntNo")
    fun findByMgmtInstNoAndMgmtInstAcntNo(custMgmtInstNo: String, custMgmtInstAcntNo: String): SecAccountValidate?

}
