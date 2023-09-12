package io.medium.poc.domain.user.service

import io.medium.poc.domain.user.model.entity.ComUser
import io.medium.poc.domain.user.repository.ComUserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReadComUserService(
    private val comUserRepository: ComUserRepository,
) {

    fun findUserById(id: String): ComUser? {
        return comUserRepository.findByIdOrNull(id)
    }

}
