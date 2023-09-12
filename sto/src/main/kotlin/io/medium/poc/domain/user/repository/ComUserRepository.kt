package io.medium.poc.domain.user.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.user.model.entity.ComUser

/**
 * 공통 사용자 repository
 */
interface ComUserRepository: BaseRepository<ComUser, String>