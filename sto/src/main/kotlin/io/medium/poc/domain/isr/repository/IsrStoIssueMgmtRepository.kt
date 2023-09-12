package io.medium.poc.domain.isr.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.isr.model.entity.IsrStoIssueMgmt
import io.medium.poc.domain.isr.model.entity.IsrStoIssueMgmtMultiKeys
import io.medium.poc.domain.isr.repository.custom.CustomIsrStoIssueMgmtRepository

interface IsrStoIssueMgmtRepository
    : BaseRepository<IsrStoIssueMgmt, IsrStoIssueMgmtMultiKeys>, CustomIsrStoIssueMgmtRepository