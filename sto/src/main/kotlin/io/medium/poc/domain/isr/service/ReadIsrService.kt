package io.medium.poc.domain.isr.service

import io.medium.poc.common.extensions.findByIdOrThrow
import io.medium.poc.common.utils.notFoundEntity
import io.medium.poc.domain.isr.model.dto.*
import io.medium.poc.domain.isr.model.entity.*
import io.medium.poc.domain.isr.repository.IsrStoIssueAplctRepository
import io.medium.poc.domain.isr.repository.IsrStoIssueMgmtRepository
import io.medium.poc.domain.isr.repository.IsrStoIssueRepository
import io.medium.poc.domain.isr.repository.IsrStoSbscrAssignRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadIsrService(
    private val isrStoIssueAplctRepository: IsrStoIssueAplctRepository,
    private val isrStoIssueRepository: IsrStoIssueRepository,
    private val isrStoIssueMgmtRepository: IsrStoIssueMgmtRepository,
    private val isrStoSbscrAssignRepository: IsrStoSbscrAssignRepository,
) {

    @Transactional(readOnly = true)
    fun isrStoIssueApplications(
        searchCondition: String,
        recordsCount: Long,
        bookmark: String?,
    ): List<IsrStoIssueAplctDto> {
        return isrStoIssueAplctRepository.isrStoIssueApplications(searchCondition, recordsCount, bookmark)
    }

    @Transactional(readOnly = true)
    fun isrStoIssue(issueNo: String, issueMgmtInstNo: String, issueMgmtInstAcntNo: String): IsrStoIssueDto {
        val errorMessage = "발행인번호[$issueNo], 발행관리기관번호[$issueMgmtInstNo], 발행관리기관계좌번호[$issueMgmtInstAcntNo]로 조회된 정보가 없습니다."
        return isrStoIssueRepository.isrStoIssue(issueNo, issueMgmtInstNo, issueMgmtInstAcntNo)
            ?: notFoundEntity(errorMessage)
    }

    /** 발행사 게재정보접수_발행인정보조회 */
    @Transactional(readOnly = true)
    fun getIssuerInfo(issueNo:String): IssuerInfoDto {
        val errorMessage = "발행인번호[$issueNo]로 조회된 정보가 없습니다."
        return isrStoIssueRepository.findByIssueNo(issueNo) ?: notFoundEntity(errorMessage)
    }

    /** 발행사 게재정보접수_게재정보조회 */
    @Transactional(readOnly = true)
    fun getIsrPubInfo(issueMgmtInstNo: String, issueMgmtInstAcntNo: String, stoItemNo: String,issueNo: String): IsrPubInfoDto {
        val errorMessage = "발행인관리기관번호[$issueMgmtInstNo], 발행인관리기관계좌번호[$issueMgmtInstAcntNo], STO종목번호[$stoItemNo], 발행인번호[$issueNo]로 조회된 정보가 없습니다."
        return isrStoIssueMgmtRepository.findIsrPubInfo(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo, issueNo) ?: notFoundEntity(errorMessage)
    }

    /** 발행사 ISR_STO_ISSUE 테이블의 ID인 ISSUE_NO 채번 */
    @Transactional(readOnly = true)
    fun isrRetrieveIssueNo(): String {
        return isrStoIssueRepository.retrieveIssueNo()
    }

    /** 발행사 투자자배정신청_투자자배정신청정보조회 */
    @Transactional(readOnly = true)
    fun getIsrInvestors(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo:String,
        recordsCount: Long,
        bookmark:Long
    ): List<IsrInvestorListDto> {
        return isrStoSbscrAssignRepository.getIsrInvestors(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo, recordsCount, bookmark)
    }

    /** 발행사 투자자배정신청_투자자배정신청정보조회 청약 query */
    @Transactional(readOnly = true)
    fun getIsrInvestorInfo(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): IsrInvestorInfoDto {
        return isrStoSbscrAssignRepository.getIsrInvestorInfo(
            issueMgmtInstNo,
            issueMgmtInstAcntNo,
            stoItemNo,
        )
    }

    @Transactional(readOnly = true)
    fun getIsrStoIssueById(issueNo: String): IsrStoIssue {
        val errorMessage = "발행인번호[$issueNo]로 조회된 정보가 없습니다."
        return isrStoIssueRepository.findByIdOrThrow(issueNo, errorMessage)
    }

    @Transactional(readOnly = true)
    fun getIsrStoIssueAplctById(composeId: IsrStoIssueAplctMultiKeys): IsrStoIssueAplct {
        val errorMessage = with(composeId) {
            "발행관리기관번호[$issueMgmtInstNo], ]발행관리기관계좌번호[$issueMgmtInstAcntNo], 발행인번호[$issueNo]로 조회된 정보가 없습니다. "
        }
        return isrStoIssueAplctRepository.findByIdOrThrow(composeId, errorMessage)
    }

    @Transactional(readOnly = true)
    fun getIsrStoIssueMgmtById(composeId: IsrStoIssueMgmtMultiKeys): IsrStoIssueMgmt {
        val errorMessage = with(composeId) {
            "발행관리기관번호[$issueMgmtInstNo], ]발행관리기관계좌번호[$issueMgmtInstAcntNo], STO 종목번호[$stoItemNo]로 조회된 정보가 없습니다. "
        }
        return isrStoIssueMgmtRepository.findByIdOrThrow(composeId, errorMessage)
    }

    @Transactional(readOnly = true)
    fun batchForIsrStoSbscrAssigns(
        issueMgmtInstNo: String,
        issueMgmtInstAcntNo: String,
        stoItemNo: String,
    ): List<IsrStoSbscrAssign> {
        return isrStoSbscrAssignRepository.batchForIsrStoSbscrAssigns(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)
    }

}
