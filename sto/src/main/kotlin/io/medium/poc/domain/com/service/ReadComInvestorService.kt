package io.medium.poc.domain.com.service

import io.medium.poc.domain.com.model.entity.ComInvestor
import io.medium.poc.domain.com.repository.ComInvestorRepository
import org.springframework.stereotype.Service

/**
 * com investor 서비스
 * created by basquiat
 */
@Service
class ReadComInvestorService(
    private val comInvestorRepository: ComInvestorRepository,
) {

    fun comInvestors(): List<ComInvestor> = comInvestorRepository.findAll()

}
