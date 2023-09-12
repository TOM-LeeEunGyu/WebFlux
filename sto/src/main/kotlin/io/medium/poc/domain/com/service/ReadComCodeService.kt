package io.medium.poc.domain.com.service

import io.medium.poc.common.extensions.findByIdOrThrow
import io.medium.poc.domain.com.model.entity.ComCode
import io.medium.poc.domain.com.model.entity.ComCodeMultiKeys
import io.medium.poc.domain.com.repository.ComCodeRepository
import org.springframework.stereotype.Service

/**
 * com code 서비스
 * created by basquiat
 */
@Service
class ReadComCodeService(
    private val comCodeRepository: ComCodeRepository,
) {

    fun findById(id: ComCodeMultiKeys): ComCode = comCodeRepository.findByIdOrThrow(id)

}
