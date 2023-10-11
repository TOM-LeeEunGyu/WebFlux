package io.dustin.domain.user.service

import io.dustin.common.extensions.findByIdOrThrow
import io.dustin.domain.user.repository.UserRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Service

@Service
class ReadUserService(
    private val userRepository: UserRepository
) {

    /**
     * findById와 확장 함수로 생성한 findByIdOrThrow로 나눈 이유는 하나이다.
     * 기본적으로 해당 API를 사용할 때 전역으로 처리해야 하는 경우 외에 비지니스 로직내에서 던지지 말아야 하는 경우도 발생할 수 있다.
     */
    fun users(pageable: Pageable) = userRepository.findAllBy(pageable)

    fun userById(id: Long) = userRepository.findById(id)

    fun userByIdOrThrow(id: Long) = userRepository.findByIdOrThrow(id)

    fun totalCount() = userRepository.count()

    fun usersByQuery(match: Query) = userRepository.usersByQuery(match)

    fun totalCountByQuery(match: Query) = userRepository.totalCountByQuery(match)


}