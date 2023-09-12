package io.medium.poc.common.schedule

import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.external.IsrCall
import io.medium.poc.common.model.request.TransferToIsrApproveStoIssue
import io.medium.poc.common.model.request.TransferToIsrApproveStoIssueMgmt
import io.medium.poc.common.utils.logger
import io.medium.poc.domain.ksd.service.ReadForBatchKsdService
import io.medium.poc.domain.ksd.service.ReadKsdService
import io.medium.poc.domain.ksd.service.WriteKsdService
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * 예탁원
 */
@Component
class StoScheduler(
    private val readForBatchKsdService: ReadForBatchKsdService,
    private val readKsdService: ReadKsdService,
    private val writeKsdService: WriteKsdService,
    private val isrCall: IsrCall,
) {

    private val log = logger<StoScheduler>()

    /**
     * 예탁원 공모승인 배치
     */
    @Scheduled(fixedDelay = 60000)
    fun aplctApproveScheduler() = runBlocking {
        log.info("예탁원 공모승인 배치 시작....")
        val channel = produce {
            readForBatchKsdService.fetchKsdStoIssueAplctNotYetSend().forEach { ksdStoIssueAplct ->
                launch {
                    val ksdStoIssue = readKsdService.fetchKsdStoIssueById(ksdStoIssueAplct.compositeId.issueNo)
                    send(ksdStoIssueAplct to ksdStoIssue)
                }
            }

        }

        launch {
            channel.consumeEach { pair ->
                val ksdStoIssueAplct = pair.first
                ksdStoIssueAplct.sndYn = YesOrNo.Y
                val ksdStoIssue = pair.second
                ksdStoIssue.sndYn = YesOrNo.Y
                writeKsdService.saveKsdStoIssue(ksdStoIssue)
                writeKsdService.saveKsdStoIssueAplct(ksdStoIssueAplct)
                val transfer = with(ksdStoIssueAplct) {
                    TransferToIsrApproveStoIssue(
                        issueMgmtInstNo = compositeId.issueMgmtInstNo,
                        issueMgmtInstAcntNo = compositeId.issueMgmtInstAcntNo,
                        issueNo = compositeId.issueNo,
                        stoItemNo = stoItemNo,
                        issueApprDt = issueApprDt!!,
                        apprYn = apprYn!!,
                    )
                }
                isrCall.receiveApproveIssueDataFromKsd(transfer)
            }
        }
    }

    /**
     * 예탁원 게제승인 배치
     */
    @Scheduled(fixedDelay = 60000)
    fun mgmtApproveScheduler() = runBlocking {
        log.info("예탁원 게제승인 배치 시작....")
        val channel = produce {
            readForBatchKsdService.fetchKsdStoIssueMgmtNotYetSend().forEach { ksdStoIssueMgmt ->
                launch {
                    send(ksdStoIssueMgmt)
                }
            }

        }

        launch {
            channel.consumeEach { ksdStoIssueMgmt ->
                ksdStoIssueMgmt.sndYn = YesOrNo.Y
                writeKsdService.saveKsdStoIssueMgmt(ksdStoIssueMgmt)
                val ksdStoIssue = readKsdService.fetchKsdStoIssueById(ksdStoIssueMgmt.issueNo!!)
                val transfer = with(ksdStoIssueMgmt) {
                    TransferToIsrApproveStoIssueMgmt(
                        issueMgmtInstNo = compositeId.issueMgmtInstNo,
                        issueMgmtInstAcntNo = compositeId.issueMgmtInstAcntNo,
                        stoItemNo = compositeId.stoItemNo,
                        issueStatusType = issueStatusType,
                        lstIssueDt = ksdStoIssue.lstIssueDt,
                        issueApprDt = issueApprDt,
                        apprYn = apprYn,
                    )
                }
                isrCall.receiveApproveIssueMgmtDataFromKsd(transfer)
            }
        }
    }

}
