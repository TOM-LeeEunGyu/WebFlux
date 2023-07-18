package io.dustin.apps.common.repository

import org.springframework.data.jpa.repository.JpaRepository

@NoRepositoryBean
interface BaseRepository<M, I : Serializable?> : JpaRepository<M?, I?>, JpaSpecificationExecutor<M?> 