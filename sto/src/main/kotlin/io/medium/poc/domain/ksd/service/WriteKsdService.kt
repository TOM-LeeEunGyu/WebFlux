package io.medium.poc.domain.ksd.service

import io.medium.poc.domain.ksd.model.entity.*
import io.medium.poc.domain.ksd.repository.*
import org.springframework.stereotype.Service

@Service
class WriteKsdService(
    private val ksdStoIssueAplctRepository: KsdStoIssueAplctRepository,
    private val ksdStoIssueRepository: KsdStoIssueRepository,
    private val ksdStoIssueMgmtRepository: KsdStoIssueMgmtRepository,
    private val ksdStoSbscrAplctRepository: KsdStoSbscrAplctRepository,
    private val ksdStoSbscrAssignRepository: KsdStoSbscrAssignRepository,
) {

    fun saveKsdStoIssue(entity: KsdStoIssue): KsdStoIssue {
        return ksdStoIssueRepository.save(entity)
    }

    fun saveKsdStoIssueAplct(entity: KsdStoIssueAplct): KsdStoIssueAplct {
        return ksdStoIssueAplctRepository.save(entity)
    }

    fun saveKsdStoIssueMgmt(entity: KsdStoIssueMgmt): KsdStoIssueMgmt {
        return ksdStoIssueMgmtRepository.save(entity)
    }

    fun saveKsdStoSbscrAplct(entity: KsdStoSbscrAplct): KsdStoSbscrAplct {
        return ksdStoSbscrAplctRepository.save(entity)
    }

    fun saveKsdStoSbscrAssign(entity: KsdStoSbscrAssign): KsdStoSbscrAssign {
        return ksdStoSbscrAssignRepository.save(entity)
    }

}
