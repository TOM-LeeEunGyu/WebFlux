package io.dustin.apps.common.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface BaseRepository<M, I : Serializable?> : JpaRepository<M, I>, JpaSpecificationExecutor<M>