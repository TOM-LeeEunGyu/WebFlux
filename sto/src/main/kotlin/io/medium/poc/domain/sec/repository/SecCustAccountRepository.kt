package io.medium.poc.domain.sec.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.sec.model.entity.SecCustAccount
import io.medium.poc.domain.sec.model.entity.SecCustAccountMultiKeys
import io.medium.poc.domain.sec.repository.custom.CustomSecCustAccountRepository

interface SecCustAccountRepository
    : BaseRepository<SecCustAccount, SecCustAccountMultiKeys>, CustomSecCustAccountRepository
