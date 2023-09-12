package io.medium.poc.api.usecase.ksd

import io.medium.poc.api.controller.ksd.request.command.KsdStoIssueApproveCommand
import io.medium.poc.common.exception.UniqueValueException
import io.medium.poc.common.utils.nowLocalDate
import io.medium.poc.domain.com.model.entity.ComStoItem
import io.medium.poc.domain.com.service.ReadComStoItemService
import io.medium.poc.domain.com.service.WriteComStoItemService
import io.medium.poc.domain.ksd.service.ReadKsdService
import io.medium.poc.domain.ksd.service.WriteKsdService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteApproveKsdIssueAplctUseCase(
    private val readKsdService: ReadKsdService,
    private val writeKsdService: WriteKsdService,
    private val readComStoItemService: ReadComStoItemService,
    private val writeComStoItemService: WriteComStoItemService,
) {

    @Transactional
    fun execute(command: KsdStoIssueApproveCommand) {
        duplicateComStoItemCheck(command.stoItemNo)

        val ksdStoIssueAplct = readKsdService.fetchKsdStoIssueAplctForApprove(command.compositeId())
        ksdStoIssueAplct.apprYn = command.apprYn()
        ksdStoIssueAplct.issueApprDt = nowLocalDate()
        ksdStoIssueAplct.stoItemNo = command.stoItemNo
        writeKsdService.saveKsdStoIssueAplct(ksdStoIssueAplct)

        val ksdStoIssue = readKsdService.fetchKsdStoIssueById(command.issueNo)
        ksdStoIssue.apprYn = command.apprYn()
        writeKsdService.saveKsdStoIssue(ksdStoIssue)

        val comStoItem = ComStoItem(
            stoItemNo = command.stoItemNo,
            stoItemName = ksdStoIssueAplct.stoItemName,
            stoBaseAssetType = ksdStoIssueAplct.stoBaseAssetType,
        )
        writeComStoItemService.saveComStoItem(comStoItem)
    }

    private fun duplicateComStoItemCheck(stoItemNo: String) {
        if(readComStoItemService.checkExistsComStoItem(stoItemNo)) {
            throw UniqueValueException("STO 종목번호[$stoItemNo]가 이미 존재합니다.")
        }
    }

}
