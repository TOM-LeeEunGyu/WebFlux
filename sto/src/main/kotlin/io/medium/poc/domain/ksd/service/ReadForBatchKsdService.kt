package io.medium.poc.domain.ksd.service

import io.medium.poc.common.code.YesOrNo
import io.medium.poc.domain.ksd.model.entity.KsdStoIssue
import io.medium.poc.domain.ksd.model.entity.KsdStoIssueAplct
import io.medium.poc.domain.ksd.model.entity.KsdStoIssueMgmt
import io.medium.poc.domain.ksd.repository.KsdStoIssueAplctRepository
import io.medium.poc.domain.ksd.repository.KsdStoIssueMgmtRepository
import io.medium.poc.domain.ksd.repository.KsdStoIssueRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadForBatchKsdService(
    private val ksdStoIssueAplctRepository: KsdStoIssueAplctRepository,
    private val ksdStoIssueRepository: KsdStoIssueRepository,
    private val ksdStoIssueMgmtRepository: KsdStoIssueMgmtRepository,
) {

    /**
     * 배치를 위한 API
     * 예탁원에서 공모 승인이후 아직 발행사로 정보를 전송하지 않은 리스트를 가져온다.
     */
    @Transactional(readOnly = true)
    fun fetchKsdStoIssueAplctNotYetSend(): List<KsdStoIssueAplct> {
        return ksdStoIssueAplctRepository.findBySndYnAndIssueApprDtIsNotNull(YesOrNo.N)
    }

    @Transactional(readOnly = true)
    fun fetchKsdStoIssueNotYetSend(): List<KsdStoIssue> {
        return ksdStoIssueRepository.findBySndYn(YesOrNo.N)
    }

    @Transactional(readOnly = true)
    fun fetchKsdStoIssueMgmtNotYetSend(): List<KsdStoIssueMgmt> {
        return ksdStoIssueMgmtRepository.findBySndYnAndIssueApprDtIsNotNull(YesOrNo.N)
    }

}
