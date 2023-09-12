package io.medium.poc.domain.isr.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.isr.model.entity.IsrStoSbscrAssign
import io.medium.poc.domain.isr.model.entity.IsrStoSbscrAssignMultiKeys
import io.medium.poc.domain.isr.repository.custom.CustomIsrStoSbscrAssignRepository

interface IsrStoSbscrAssignRepository: BaseRepository<IsrStoSbscrAssign, IsrStoSbscrAssignMultiKeys>, CustomIsrStoSbscrAssignRepository