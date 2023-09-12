package io.medium.poc.api.usecase.bank

import io.medium.poc.api.controller.bank.request.query.BankSettlementIssueInfoQuery
import io.medium.poc.common.model.response.ResultResponse
import io.medium.poc.common.utils.notFoundEntity
import io.medium.poc.domain.bank.model.dto.BankSettlementIssueInfoDto
import io.medium.poc.domain.bank.service.ReadBankService
import org.springframework.stereotype.Service

@Service
class ReadBankSettlementIssueInfoUseCase(
    private val readBankService: ReadBankService,
) {

    fun execute(query: BankSettlementIssueInfoQuery): ResultResponse<BankSettlementIssueInfoDto> {
        val stoItemNo = query.stoItemNo
        val errorMessage = "STO 종목번호[$stoItemNo]로 조회된 정보가 없습니다."
        val bankSettlementIssueInfoDto = readBankService.bankSettlementIssueInformation(stoItemNo)
        if(bankSettlementIssueInfoDto.stoItemNo.isNullOrBlank()) {
            notFoundEntity(errorMessage)
        }
        return ResultResponse.of(bankSettlementIssueInfoDto)
    }

}
