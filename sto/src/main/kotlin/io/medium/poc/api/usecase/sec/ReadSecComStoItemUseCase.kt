package io.medium.poc.api.usecase.sec

import io.medium.poc.api.controller.sec.request.query.SecIncinerationQuery
import io.medium.poc.common.utils.notFoundEntity
import io.medium.poc.domain.sec.model.dto.SecIncinerationDto
import io.medium.poc.domain.sec.service.ReadSecService
import org.springframework.stereotype.Service

@Service
class ReadSecComStoItemUseCase(
    private val readSecService: ReadSecService,
) {
    fun execute(query: SecIncinerationQuery): SecIncinerationDto {
        val balanceQty = with(query) {
            readSecService.balanceQtyQuery(stoItemNo, custMgmtInstNo)
        }
        val errorMessage= with(query) {
            "STO 종목번호[$stoItemNo], 고객관리기관번호[$custMgmtInstNo]로 조회된 정보가 없습니다."
        }
        return balanceQty?.let { SecIncinerationDto(it) } ?: notFoundEntity(errorMessage)
    }
}
