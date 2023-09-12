package io.medium.poc.domain.invest.service

import io.medium.poc.domain.invest.model.dto.InvestStoIssueMgmtDto
import io.medium.poc.domain.invest.model.dto.InvestStoSbscrAplctDto
import io.medium.poc.domain.invest.repository.CustomInvestRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadInvestService(
    private val customInvestRepository: CustomInvestRepository,
) {

    /**
     * 투자자 토큰증권 목록조회 리스트
     */
    @Transactional(readOnly = true)
    fun investStoIssueManagements(
        search: String,
        recordsCount: Long,
        bookmark:Long
    ): List<InvestStoIssueMgmtDto> {
        return customInvestRepository.investStoIssueManagements(search, recordsCount, bookmark)
    }

    /**
     * 투자자 청약신청조회
     */
    @Transactional(readOnly = true)
    fun investStoSbscrApplication(
        stoItemNo: String,
        custMgmtInstNo: String,
        custMgmtInstAcntNo: String,
    ): InvestStoSbscrAplctDto {
        return customInvestRepository.investStoSbscrApplication(
            stoItemNo,
            custMgmtInstNo,
            custMgmtInstAcntNo
        )
    }

}
