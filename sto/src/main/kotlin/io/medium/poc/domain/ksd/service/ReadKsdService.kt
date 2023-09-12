package io.medium.poc.domain.ksd.service

import io.medium.poc.common.extensions.findByIdOrThrow
import io.medium.poc.common.utils.notFoundEntity
import io.medium.poc.domain.ksd.model.dto.*
import io.medium.poc.domain.ksd.model.entity.*
import io.medium.poc.domain.ksd.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadKsdService(
    private val ksdStoIssueAplctRepository: KsdStoIssueAplctRepository,
    private val ksdStoIssueRepository: KsdStoIssueRepository,
    private val ksdStoIssueMgmtRepository: KsdStoIssueMgmtRepository,
    private val ksdStoSbscrAplctRepository: KsdStoSbscrAplctRepository,
    private val ksdStoSbscrAssignRepository: KsdStoSbscrAssignRepository,
) {

    @Transactional(readOnly = true)
    fun ksdStoIssueApplications(
        searchCondition: String,
        recordsCount: Long,
        bookmark: String?,
    ): List<KsdStoIssueAplctDto> {
        return ksdStoIssueAplctRepository.ksdStoIssueApplications(searchCondition, recordsCount, bookmark)
    }

    @Transactional(readOnly = true)
    fun fetchKsdStoIssueAplctForApprove(compositeId: KsdStoIssueAplctMultiKeys): KsdStoIssueAplct {
        val errorMessage = with(compositeId) {
            "발행관리기관번호[${issueMgmtInstNo}], 발행관리기관계좌번호[$issueMgmtInstAcntNo], 발행인번호[$issueNo]로 조회된 정보가 없습니다."
        }
        return ksdStoIssueAplctRepository.findByIdOrThrow(compositeId, errorMessage)
    }

    @Transactional(readOnly = true)
    fun fetchKsdStoIssue(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        issueNo: String,
    ): KsdStoIssueDto {
        val errorMessage = "발행관리기관번호[$issueMgmtInstNo], 발행관리기관계좌번호[$issueMgmtInstAcntNo], 발행인번호[$issueNo]로 조회된 정보가 없습니다."
        return ksdStoIssueRepository.ksdStoIssue(issueMgmtInstNo, issueMgmtInstAcntNo, issueNo)
            ?: notFoundEntity(errorMessage)
    }

    @Transactional(readOnly = true)
    fun ksdStoIssuePostingInformation(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): KsdPostingInfoDto {
        val errorMessage = "발행관리기관번호[$issueMgmtInstNo], 발행관리기관계좌번호[$issueMgmtInstAcntNo], STO 종목번호[$stoItemNo]로 조회된 정보가 없습니다."
        return ksdStoIssueAplctRepository.ksdStoIssuePostingInformation(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
            ?: notFoundEntity(errorMessage)
    }

    /**
     * 예탁원 게제정보 승인을 위한 대상 정보 조회
     */
    @Transactional(readOnly = true)
    fun fetchKsdStoIssueMgmtForApprove(issueMgmtInstNo: String, issueMgmtInstAcntNo: String, stoItemNo: String): KsdStoIssueMgmt {
        val errorMessage = "발행관리기관번호[$issueMgmtInstNo], 발행관리기관계좌번호[$issueMgmtInstAcntNo], STO 종목번호[$stoItemNo]로 조회된 정보가 없습니다."
        return ksdStoIssueMgmtRepository.findByIssueMgmtInstNoAndIssueMgmtInstAcntNoAndStoItemNo(
            issueMgmtInstNo,
            issueMgmtInstAcntNo,
            stoItemNo
        ) ?: notFoundEntity(errorMessage)
    }

    @Transactional(readOnly = true)
    fun fetchKsdStoIssueById(issueNo: String): KsdStoIssue {
        return ksdStoIssueRepository.findByIdOrThrow(issueNo)
    }

    /**
     * 예탁원투자자 배정 승인 목록 조회
     */
    @Transactional(readOnly = true)
    fun ksdStoSbscrApplications(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
        recordsCount: Long,
        bookmark: Long
    ): List<KsdStoSbscrAplctListDto> {
        return ksdStoSbscrAplctRepository.ksdStoSbscrApplications(
            issueMgmtInstNo,
            issueMgmtInstAcntNo,
            stoItemNo,
            recordsCount,
            bookmark
        )
    }

    /**
     * 청약 신청 인원
     */
    @Transactional(readOnly = true)
    fun ksdStoSbscrAplctInfo(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): KsdStoSbscrAplctInfoDto {
        return ksdStoSbscrAplctRepository.ksdStoSbscrAplctInfo(
            issueMgmtInstNo,
            issueMgmtInstAcntNo,
            stoItemNo,
        )
    }


    /**
     * 예탁원투자자 배정 승인 대상 STO 청약배정내역 row 조회
     */
    @Transactional(readOnly = true)
    fun fetchKsdStoSbscrAplctForApprove(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String
    ): List<KsdStoSbscrAplct> {
        return ksdStoSbscrAplctRepository.findByIssueMgmtInstNoAndIssueMgmtInstAcntNoAndStoItemNo(
            issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo
        )
    }

    /**
     * 예탁원투자자 배정 승인 대상 STO 청약신청내역 row 조회
     */
    @Transactional(readOnly = true)
    fun fetchKsdStoSbscrAssignForApprove(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String
    ): List<KsdStoSbscrAssign> {
        return ksdStoSbscrAssignRepository.findByIssueMgmtInstNoAndIssueMgmtInstAcntNoAndStoItemNo(
            issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo
        )
    }

}
