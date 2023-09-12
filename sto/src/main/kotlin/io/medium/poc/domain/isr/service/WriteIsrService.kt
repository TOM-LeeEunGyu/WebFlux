package io.medium.poc.domain.isr.service

import io.medium.poc.domain.isr.model.entity.*
import io.medium.poc.domain.isr.repository.*
import org.springframework.stereotype.Service

@Service
class WriteIsrService(
    private val isrStoIssueRepository: IsrStoIssueRepository,
    private val isrStoIssueAplctRepository: IsrStoIssueAplctRepository,
    private val isrStoIssueMgmtRepository: IsrStoIssueMgmtRepository,
    private val isrStoSbscrAssignRepository: IsrStoSbscrAssignRepository,
    private val isrStoSbscrAplctRepository: IsrStoSbscrAplctRepository,
) {

    /**
     * 발행사 공모등록시에 사용(헷갈려서 잠시 주석 달아놓겠습니다)
     */
    fun saveIsrStoIssue(entity: IsrStoIssue): IsrStoIssue {
        return isrStoIssueRepository.save(entity)
    }

    /**
     * 발행사 STO 청약신청내역 저장
     */
    fun saveIsrStoSbscrAplct(entity: IsrStoSbscrAplct): IsrStoSbscrAplct {
        return isrStoSbscrAplctRepository.save(entity)
    }

    /**
     * 발행사 공모등록시에 사용(헷갈려서 잠시 주석 달아놓겠습니다)
     */
    fun saveIsrStoIssueAplct(entity: IsrStoIssueAplct): IsrStoIssueAplct {
        return isrStoIssueAplctRepository.save(entity)
    }

    /**
     * 발행사 투자자배정신청_투자자배정신청 전송
     */
    fun saveIsrStoSbscrAssign(entity: IsrStoSbscrAssign): IsrStoSbscrAssign {
        return isrStoSbscrAssignRepository.save(entity)
    }

    /**
     * 발행사 게제정보 등록(헷갈려서 잠시 주석 달아놓겠습니다)
     */
    fun saveIsrStoIssueMgmt(entity: IsrStoIssueMgmt): IsrStoIssueMgmt{
        return isrStoIssueMgmtRepository.save(entity)
    }

}
