package io.medium.poc.domain.isr.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.isr.model.entity.IsrStoIssueAplct
import io.medium.poc.domain.isr.model.entity.IsrStoIssueAplctMultiKeys
import io.medium.poc.domain.isr.repository.custom.CustomIsrStoIssueAplctRepository

interface IsrStoIssueAplctRepository
    : BaseRepository<IsrStoIssueAplct, IsrStoIssueAplctMultiKeys>, CustomIsrStoIssueAplctRepository